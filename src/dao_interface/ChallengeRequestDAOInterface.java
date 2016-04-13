package dao_interface;

import java.util.ArrayList;

import models.ChallengeRequest;

public interface ChallengeRequestDAOInterface {
	public boolean addRequest(ChallengeRequest request);
	public boolean updateRequest(ChallengeRequest request);
	public boolean deleteRequest(ChallengeRequest request);
	public ArrayList<ChallengeRequest> getRequestsBySender(int senderID);
	public ArrayList<ChallengeRequest> getRequestsByReceiver(int receiverID);
	
	//added by CJ
	public ArrayList<ChallengeRequest> getRequestsBySenderAndReceiver(int senderID, int receiverID);
	public ArrayList<ChallengeRequest> getRequestsByReceiver(int receiverID, int pending);
}
