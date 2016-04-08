package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.LikeDAOInterface;
import models.Like;

public class LikeDAO implements LikeDAOInterface {

	@Override
	public boolean addLike(Like like) {
		// TODO Auto-generated method stub
		try{
			
			String query = "insert into liketable values(?,?)";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,like.getAccountID());	
			preparedStmt.setInt(2,like.getPostID());
			
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
	public boolean deleteLike(Like like) {
		// TODO Auto-generated method stub
		try{
			String query="delete from liketable where Account_idAccount=? and Post_idPost=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,like.getAccountID());	
			preparedStmt.setInt(2,like.getPostID());
			
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
	public boolean checkIfUserLikesPost(int accountID, int postID){
		// TODO Auto-generated method stub
		try{
			String query="select * from liketable where Account_idAccount=? and Post_idPost=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,accountID);	
			preparedStmt.setInt(2,postID);
			int[] topass=new int[2];
			ResultSet temp = preparedStmt.executeQuery();
			while (temp.next()) {
				topass[0]=temp.getInt("Account_idAccount");
				topass[1]=temp.getInt("Post_idPost");
			}
			preparedStmt.close();
			con.close();
					if(accountID==topass[0] && postID==topass[1])
					{
						return true;
					}
					else
						return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
