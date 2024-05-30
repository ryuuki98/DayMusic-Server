package image.controller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class ImageServiceServlet extends HttpServlet {

	private static final String S3_BUCKET_NAME = "daymusic-image-upload-bucket";
	private static final String AWS_ACCESS_KEY = "your-iam-user-access-key";
	private static final String AWS_SECRET_KEY = "your-iam-user-secret-key";
	private static final String AWS_REGION = "ap-northeast-2";
	private static final String DB_URL = "jdbc:mysql://your-db-instance-url:3306/your-db-name";
	private static final String DB_USER = "your-db-user";
	private static final String DB_PASSWORD = "your-db-password";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이미지 업로드 시작 ");

		if (!ServletFileUpload.isMultipartContent(request)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form must have enctype=multipart/form-data.");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		String userId = null;
		InputStream fileContent = null;
		String fileName = null;

		try {
			List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					if (item.isFormField()) {
						if (item.getFieldName().equals("userId")) {
							userId = item.getString();
						}
					} else {
						fileName = new File(item.getName()).getName();
						fileContent = item.getInputStream();
					}
				}
			}

			System.out.println("userId: " + userId);

			if (userId == null || fileContent == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing form data");
				return;
			}

			// AWS S3에 파일 업로드
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withRegion(AWS_REGION)
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.build();

			String s3FileName = System.currentTimeMillis() + "_" + fileName;
			s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, s3FileName, fileContent, null)
					.withCannedAcl(CannedAccessControlList.PublicRead));

			String imageUrl = s3Client.getUrl(S3_BUCKET_NAME, s3FileName).toString();

			// RDS에 URL 저장
			try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				String sql = "UPDATE users SET profile_img_url = ? WHERE id = ?";
				try (PreparedStatement statement = connection.prepareStatement(sql)) {
					statement.setString(1, imageUrl);
					statement.setString(2, userId);
					statement.executeUpdate();
				}
			}

			response.getWriter().write("Profile image uploaded and URL saved to DB: " + imageUrl);

		} catch (Exception ex) {
			throw new ServletException("File upload failed", ex);
		}
	}
}
