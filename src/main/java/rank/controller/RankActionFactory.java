	package rank.controller;

    import like.controller.action.LikeAction;
    import like.controller.action.LikeAddAction;
    import like.controller.action.LikeListAction;
    import rank.controller.action.RankListAction;
    import rank.controller.action.RankSideAction;

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

        public RankAction getAction(String command){
            RankAction action = null;

            if(command.equals("rank")){
                
                action = new RankListAction();
            }else if(command.equals("sideRank")){
                
                action = new RankSideAction();
            }
            return action;
        }
    }