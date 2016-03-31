package dao_interface;

import java.util.ArrayList;

import models.Comment;

public interface CommentDAOInterface {
	public boolean addComment(Comment comment);
	public boolean deleteComment(Comment comment);
	public ArrayList<Comment> getCommentsByAccount(int accountID);
	public ArrayList<Comment> getCommentsByPost(int postID);
}
