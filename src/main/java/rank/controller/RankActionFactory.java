	package rank.controller;

    import like.controller.action.LikeAction;
    import like.controller.action.LikeAddAction;
    import like.controller.action.LikeListAction;
    import rank.controller.action.RankListAction;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    public class RankActionFactory {
        private RankActionFactory() {

        }

        private static RankActionFactory instance = new RankActionFactory();

        public static RankActionFactory getInstance() {
            return instance;
        }

        public RankAction getAtion(String command){
            RankAction action = null;

            if(command.equals("search")){
                System.out.println("랭킹리스트 액션실행");
                action = new RankListAction();
            }
            return action;
        }
    }