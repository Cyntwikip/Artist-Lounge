package dao_interface;

import java.util.ArrayList;

import models.FriendRequest;

public interface FriendRequestDAOInterface {
	public boolean addRequest(FriendRequest request);
	public boolean updateRequest(FriendRequest request);
	public boolean deleteRequest(FriendRequest request);
	public ArrayList<FriendRequest> getRequestsBySender(int senderID);
	public ArrayList<FriendRequest> getRequestsByReceiver(int receiverID);
	
	//added by CJ
	public ArrayList<FriendRequest> getRequestsBySenderAndReceiver(int senderID, int receiverID);
	public ArrayList<FriendRequest> getRequestsByReceiver(int receiverID, int pending);
	
	//me
	public boolean checkIfRequestSent(int senderID, int receiverID);
	public boolean checkIfRequestIsAccepted(int senderID, int receiverID);
	public boolean checkIfUserSentRequest(int senderID, int receiverID);
	
}
