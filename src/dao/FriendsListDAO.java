package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.FriendsListDAOInterface;
import models.FriendsList;

public class FriendsListDAO implements FriendsListDAOInterface {
	
	@Override
	public boolean addFriends(FriendsList friends) {
		// TODO Auto-generated method stub
		try{
			
			String query = "insert into friendslist values(?,?)";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,friends.getUser1());	
			preparedStmt.setInt(2,friends.getUser2());
			
			preparedStmt.execute();
			preparedStmt.close();
			con.close();	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
			return true;
	}

	@Override
	public boolean deleteFriends(FriendsList friends) {
		// TODO Auto-generated method stub
		try{
			String query="delete from friendslist where user1=? and user2=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,friends.getUser1());	
			preparedStmt.setInt(2,friends.getUser2());
			
			preparedStmt.execute();
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
			return true;
	}

	@Override
	public int[] getFriendsByID(int id) {
		// TODO Auto-generated method stub
		try{
			
			ArrayList<Integer> idlist= new ArrayList<>();
			String query="select user2 from friendslist where user1=? union select user1 from friendslist where user2=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,id);	
			
			ResultSet temp = preparedStmt.executeQuery();
			while (temp.next()) {
				idlist.add(temp.getInt("user2"));
			}
			int[] topass=new int[idlist.size()];
			for(int i=0; i<idlist.size();i++)
			{
				topass[i]=idlist.get(i);
			}
			preparedStmt.close();
			con.close();
			return topass;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
