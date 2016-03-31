package models;

import java.sql.Date;

public class Comment {
	private int id;
	private int accountID;
	private int postID;
	private String comment;
	private Date time;
	
	public Comment() {
		super();
	}

	public Comment(int accountID, int postID, String comment) {
		super();
		this.accountID = accountID;
		this.postID = postID;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
