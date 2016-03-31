package models;

import java.sql.Date;

public class Post {
	private int id;
	private Date time;
	private String paint;
	private String description;
	private boolean privacy;
	private int accountID;
	private int challengeID;
	
	public Post() {
		super();
	}

	public Post(String paint, boolean privacy, int accountID) {
		super();
		this.paint = paint;
		this.privacy = privacy;
		this.accountID = accountID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPaint() {
		return paint;
	}
	public void setPaint(String paint) {
		this.paint = paint;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPrivacy() {
		return privacy;
	}
	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public int getChallengeID() {
		return challengeID;
	}
	public void setChallengeID(int challengeID) {
		this.challengeID = challengeID;
	}
}
