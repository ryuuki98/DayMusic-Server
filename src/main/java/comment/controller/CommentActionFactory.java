package comment.controller;

import comment.controller.action.CommentAddAction;
import comment.controller.action.CommentDeleteAction;
import comment.controller.action.CommentModifyAction;
import comment.controller.action.GetCommentListAction;

public class CommentActionFactory {
    private CommentActionFactory() {

    }

    private static CommentActionFactory instance = new CommentActionFactory();

    public static CommentActionFactory getInstance() {
        return instance;
    }

    public CommentAction getAction(String command) {
        CommentAction action = null;

        if (command.equals("add"))
            action = new CommentAddAction();
        else if (command.equals("edit"))
            action = new CommentModifyAction();
        else if (command.equals("delete"))
            action = new CommentDeleteAction();
        else if (command.equals("list"))
            action = new GetCommentListAction();


        return action;

    }
}
