package models;

import java.sql.Date;

public class ChallengeRequest {
	private int senderID;
	private int receiverID;
	private boolean pending;
	private Date time;
	
	public ChallengeRequest() {
		super();
	}

	public ChallengeRequest(int senderID, int receiverID) {
		super();
		this.senderID = senderID;
		this.receiverID = receiverID;
	}
	
	public int getSenderID() {
		return senderID;
	}
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	public int getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(int receiverID) {
		this.receiverID = receiverID;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}	
}
