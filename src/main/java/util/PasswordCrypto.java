package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordCrypto {
	
	public static String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean decrypt(String password, String encryptedPassword) {
		
		return BCrypt.checkpw(password, encryptedPassword);
	}
}
