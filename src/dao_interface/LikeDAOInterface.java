package dao_interface;

import models.Like;

public interface LikeDAOInterface {
	public boolean addLike(Like like);
	public boolean deleteLike(Like like);
	
	//changes
	//public int[] getPostsByAccount(int accountID);
	public boolean checkIfUserLikesPost(int accountID, int postID);
}
