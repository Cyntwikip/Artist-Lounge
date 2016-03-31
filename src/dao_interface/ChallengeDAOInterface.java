package dao_interface;

import models.Challenge;

public interface ChallengeDAOInterface {
	public int addChallenge(Challenge challenge);
	public int updateChallenge(Challenge challenge);
	public int deleteChallenge(Challenge challenge);
	public Challenge getChallengeByID(int id);
}
