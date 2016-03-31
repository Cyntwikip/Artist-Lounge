package dao_interface;

import models.FriendsList;

public interface FriendsListDAOInterface {
	public boolean addFriends(FriendsList friends);
	public boolean deleteFriends(FriendsList friends);
	public int[] getFriendsByID(int id);
}
