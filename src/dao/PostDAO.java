package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.PostDAOInterface;
import models.Post;

public class PostDAO implements PostDAOInterface {

	@Override
	public boolean addPost(Post post) {
		// TODO Auto-generated method stub
		try{
			String query="insert into post (idPost,time,paint,description,private,Account_idAccount,Challenge_idChallenge)"
			+" values(?,?,?,?,?,?,?)";
			
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,currentTime);
			preparedStmt.setString(3,post.getPaint());
			preparedStmt.setString(4,post.getDescription());
			preparedStmt.setBoolean(5, post.isPrivacy());
			preparedStmt.setInt(6, post.getAccountID());
			if(post.getChallengeID()!=-1)
			preparedStmt.setInt(7,post.getChallengeID());
			else
			preparedStmt.setNull(7, java.sql.Types.INTEGER);
			
			preparedStmt.execute();
			preparedStmt.close();
			con.close();	
			return true;

		}
		catch(SQLException e){
			e.printStackTrace();
			return false;				
		}
	}

	@Override
	public boolean updatePost(Post post) {
		// TODO Auto-generated method stub
		try{	
		
		String query="update post set time=ifnull(?,time),paint=ifnull(?,paint),description=ifnull(?,description),private=ifnull(?,private) where idPost=?";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		preparedStmt.setString(1,currentTime);
		preparedStmt.setString(2,post.getPaint());
		preparedStmt.setString(3,post.getDescription());
		preparedStmt.setBoolean(4,post.isPrivacy());
		
		preparedStmt.setInt(5,post.getId());
		preparedStmt.execute();
		preparedStmt.close();
		con.close();	
		return true;
		
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;				
		}
	}

	@Override
	public boolean deletePost(Post post) {
		// TODO Auto-generated method stub
		try{
			
		
		String query = " Delete from post Where idPost=?";
		Connection con = JDBCMySQLConnection.getConnection();
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,post.getId());
		
		preparedStmt.execute();
		preparedStmt.close();
		con.close();
		
		
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
			
		}
		return false;
	}

	@Override
	public Post getPostById(int id) {
		// TODO Auto-generated method stub
		try{
			String query = " select * from post Where idPost=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,id);
			ResultSet temp = preparedStmt.executeQuery();
			
			Post topass=new Post();
			while (temp.next()) {
				topass.setId(temp.getInt("idPost"));
				topass.setTime(temp.getDate("time"));
				topass.setPaint(temp.getString("paint"));
				topass.setDescription(temp.getString("description"));
				topass.setPrivacy(temp.getBoolean("private"));
				topass.setAccountID(temp.getInt("Account_idAccount"));
				topass.setChallengeID(temp.getInt("Challenge_idChallenge"));
			}
			preparedStmt.close();
			con.close();
			return topass;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ArrayList<Post> getPostsByChallenge(int challengeID) {
		// TODO Auto-generated method stub
		
		ArrayList<Post>	postchallengelist=new ArrayList<Post>();
		ArrayList<Integer> idpostlist=new ArrayList<>();
		ArrayList<Date>	timelist=new ArrayList<Date>();
		ArrayList<String> paintlist=new ArrayList<String>();
		ArrayList<String> descriptionlist=new ArrayList<String>();
		ArrayList<Boolean> privatelist=new ArrayList<Boolean>();
		ArrayList<Integer> accountidlist=new ArrayList<>();
		ArrayList<Integer> challengeidlist=new ArrayList<>();
		
		
		
		try{
			String query= "select * from post where Challenge_idChallenge=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,challengeID);
			
			ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			idpostlist.add(temp.getInt("idPost"));
			timelist.add(temp.getDate("time"));
			paintlist.add(temp.getString("paint"));
			descriptionlist.add(temp.getString("description"));
			privatelist.add(temp.getBoolean("private"));
			accountidlist.add(temp.getInt("Account_idAccount"));
			challengeidlist.add(temp.getInt("Challenge_idChallenge"));
			}
			for(int i=0;i<idpostlist.size();i++){
				Post t1=new Post();
				t1.setId(idpostlist.get(i));
				t1.setTime(timelist.get(i));
				t1.setPaint(paintlist.get(i));
				t1.setDescription(descriptionlist.get(i));
				t1.setPrivacy(privatelist.get(i));
				t1.setAccountID(accountidlist.get(i));
				t1.setChallengeID(challengeidlist.get(i));
				postchallengelist.add(t1);
			}
			
			preparedStmt.close();
			con.close();
			return	postchallengelist;
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Post> getPostsByText(String text) {
		// TODO Auto-generated method stub
		ArrayList<Post>	postchallengelist=new ArrayList<Post>();
		ArrayList<Integer> idpostlist=new ArrayList<>();
		ArrayList<Date>	timelist=new ArrayList<Date>();
		ArrayList<String> paintlist=new ArrayList<String>();
		ArrayList<String> descriptionlist=new ArrayList<String>();
		ArrayList<Boolean> privatelist=new ArrayList<Boolean>();
		ArrayList<Integer> accountidlist=new ArrayList<>();
		ArrayList<Integer> challengeidlist=new ArrayList<>();
		
		
		
		try{
			String query= "select * from post where description LIKE ?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,"%"+text+"%");
			
			ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			idpostlist.add(temp.getInt("idPost"));
			timelist.add(temp.getDate("time"));
			paintlist.add(temp.getString("paint"));
			descriptionlist.add(temp.getString("description"));
			privatelist.add(temp.getBoolean("private"));
			accountidlist.add(temp.getInt("Account_idAccount"));
			challengeidlist.add(temp.getInt("Challenge_idChallenge"));
			}
			for(int i=0;i<idpostlist.size();i++){
				Post t1=new Post();
				t1.setId(idpostlist.get(i));
				t1.setTime(timelist.get(i));
				t1.setPaint(paintlist.get(i));
				t1.setDescription(descriptionlist.get(i));
				t1.setPrivacy(privatelist.get(i));
				t1.setAccountID(accountidlist.get(i));
				t1.setChallengeID(challengeidlist.get(i));
				postchallengelist.add(t1);
			}
			
			preparedStmt.close();
			con.close();
			return	postchallengelist;
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Post> getRecentPosts(int number){
		ArrayList<Post>	recentpostlist=new ArrayList<Post>();
		ArrayList<Integer> idpostlist=new ArrayList<>();
		ArrayList<Date>	timelist=new ArrayList<Date>();
		ArrayList<String> paintlist=new ArrayList<String>();
		ArrayList<String> descriptionlist=new ArrayList<String>();
		ArrayList<Boolean> privatelist=new ArrayList<Boolean>();
		ArrayList<Integer> accountidlist=new ArrayList<>();
		ArrayList<Integer> challengeidlist=new ArrayList<>();
		try{
			String query= "select * from post order by time DESC limit ?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,number);
			
			ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			idpostlist.add(temp.getInt("idPost"));
			timelist.add(temp.getDate("time"));
			paintlist.add(temp.getString("paint"));
			descriptionlist.add(temp.getString("description"));
			privatelist.add(temp.getBoolean("private"));
			accountidlist.add(temp.getInt("Account_idAccount"));
			challengeidlist.add(temp.getInt("Challenge_idChallenge"));
			}
			for(int i=0;i<idpostlist.size();i++){
				Post t1=new Post();
				t1.setId(idpostlist.get(i));
				t1.setTime(timelist.get(i));
				t1.setPaint(paintlist.get(i));
				t1.setDescription(descriptionlist.get(i));
				t1.setPrivacy(privatelist.get(i));
				t1.setAccountID(accountidlist.get(i));
				t1.setChallengeID(challengeidlist.get(i));
				recentpostlist.add(t1);
			}
			
			preparedStmt.close();
			con.close();
			return	recentpostlist;
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}
	public ArrayList<Post> getPostsByAccount(int accountID){
		ArrayList<Post>	postlist=new ArrayList<Post>();
		ArrayList<Integer> idpostlist=new ArrayList<>();
		ArrayList<Date>	timelist=new ArrayList<Date>();
		ArrayList<String> paintlist=new ArrayList<String>();
		ArrayList<String> descriptionlist=new ArrayList<String>();
		ArrayList<Boolean> privatelist=new ArrayList<Boolean>();
		ArrayList<Integer> accountidlist=new ArrayList<>();
		ArrayList<Integer> challengeidlist=new ArrayList<>();
		try{
			String query= "select * from post where Account_idAccount=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,accountID);
			
			ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			idpostlist.add(temp.getInt("idPost"));
			timelist.add(temp.getDate("time"));
			paintlist.add(temp.getString("paint"));
			descriptionlist.add(temp.getString("description"));
			privatelist.add(temp.getBoolean("private"));
			accountidlist.add(temp.getInt("Account_idAccount"));
			challengeidlist.add(temp.getInt("Challenge_idChallenge"));
			}
			for(int i=0;i<idpostlist.size();i++){
				Post t1=new Post();
				t1.setId(idpostlist.get(i));
				t1.setTime(timelist.get(i));
				t1.setPaint(paintlist.get(i));
				t1.setDescription(descriptionlist.get(i));
				t1.setPrivacy(privatelist.get(i));
				t1.setAccountID(accountidlist.get(i));
				t1.setChallengeID(challengeidlist.get(i));
				postlist.add(t1);
			}
			
			preparedStmt.close();
			con.close();
			return	postlist;
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
