package models;

public class Like {
	private int accountID;
	private int postID;
	
	public Like() {
		super();
	}

	public Like(int accountID, int postID) {
		super();
		this.accountID = accountID;
		this.postID = postID;
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
}
