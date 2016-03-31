package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			con.close();	
		}
		catch(SQLException e)
		{
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
			con.close();
		}
		catch(SQLException e)
		{
			return false;
		}
			return true;
	}

	@Override
	public int[] getFriendsByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
