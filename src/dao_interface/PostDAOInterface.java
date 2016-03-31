package dao_interface;

import java.util.ArrayList;

import models.Post;

public interface PostDAOInterface {
	public boolean addPost(Post post);
	public boolean updatePost(Post post);
	public boolean deletePost(Post post);
	public Post getPostById(int id);
	public ArrayList<Post> getPostsByChallenge(int challengeID);
	public ArrayList<Post> getPostsByText(String text); 
	
	//changes
	public ArrayList<Post> getRecentPosts(int number);
	public ArrayList<Post> getPostsByAccount(int accountID);
}
