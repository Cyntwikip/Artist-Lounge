package dao_interface;

import models.Challenge;

public interface ChallengeDAOInterface {
	public int addChallenge(Challenge challenge);
	public boolean updateChallenge(Challenge challenge);
	public boolean deleteChallenge(Challenge challenge);
	public Challenge getChallengeByID(int id);
}
