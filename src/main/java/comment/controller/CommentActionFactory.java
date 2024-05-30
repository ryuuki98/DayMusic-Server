package comment.controller;

public class CommentActionFactory {
    private CommentActionFactory() {

    }

    private static CommentActionFactory instance = new CommentActionFactory();

    public static CommentActionFactory getInstance() {
        return instance;
    }

    public CommentAction getAction(String command) {
        CommentAction action = null;

//        if (command.equals("write"))
//            action = new ();

        return action;

    }
}
