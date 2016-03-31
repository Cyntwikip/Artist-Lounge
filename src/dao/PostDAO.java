package dao;

import java.util.ArrayList;

import dao_interface.PostDAOInterface;
import models.Post;

public class PostDAO implements PostDAOInterface {

	@Override
	public boolean addPost(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePost(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePost(Post post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Post getPostById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> getPostsByChallenge(int challengeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> getPostsByText(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList getRecentPosts(int number){
		return null;
	}
	public ArrayList getPostsByAccount(int accountID){
		return null;
	}
}
