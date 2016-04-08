package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.ChallengeDAOInterface;
import models.Challenge;

public class ChallengeDAO implements ChallengeDAOInterface {
	
	@Override
	public int addChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		
			try
    		{
		String query = " insert into challenge values(?,?,?,?)";

		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,0);
		preparedStmt.setString(2, challenge.getDescription() );
		preparedStmt.setDate(3, challenge.getStartTime());
		preparedStmt.setDate(4, challenge.getEndTime());
		preparedStmt.execute();
		preparedStmt.close();
		con.close();		
		}
		catch(SQLException e)
		{
		return 0;				
		}	
			
				try
		    	{
				int topass=0;
				String query = "SELECT MAX(idChallenge) FROM Challenge";
				Connection con = JDBCMySQLConnection.getConnection();
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				ResultSet temp = preparedStmt.executeQuery();
				
				while (temp.next()) {	
					topass=temp.getInt("idChallenge");
				}
				
				
				preparedStmt.close();
				con.close();
				return topass;
				}
				catch(SQLException e)
				{
				return 0;				
				}
			
			
		//insert into challenge values(0,"","","");
	}

	@Override
	public boolean updateChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		

		try
		{
			String query = "Update challenge set description=?,startTime=?,endTime=? where idChallenge=?";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1, challenge.getDescription() );
		preparedStmt.setDate(2, challenge.getStartTime());
		preparedStmt.setDate(3, challenge.getEndTime());
		preparedStmt.setInt(4, challenge.getId());
		preparedStmt.execute();
		con.close();
		return true;
		}
		catch(SQLException e)
		{
		//return challenge.getId();	
			return false;
		}	
		//return false;		
		
		//update challenge set description="",startTime="",endTime="" where idChallenge=; 
	}

	@Override
	public boolean deleteChallenge(Challenge challenge) {
		// TODO Auto-generated method stub
		
		try
		{
			String query = "delete from challenge where idChallenge=?";
		
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1, challenge.getId() );
		preparedStmt.execute();
		con.close();		
		}
		catch(SQLException e)
		{
		return false;				
		}	
		return true;
		
		//delete from challenge where idChallenge=;
	}

	@Override
	public Challenge getChallengeByID(int id) {
		// TODO Auto-generated method stub
		Challenge topass=new Challenge();
		try
		{
			Connection con = JDBCMySQLConnection.getConnection();
			String query="select * from challenge where idChallenge=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, id );
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {	
				topass.setId(temp.getInt("idChallenge"));
				topass.setDescription(temp.getString("description"));
				topass.setStartTime(temp.getDate("startTime"));
				topass.setStartTime(temp.getDate("endTime"));
			}
			con.close();		
		}
		catch(SQLException e)
		{
			System.out.println("ERROR ON GETCHALLENGEBYID");
			return null;
		}
		return topass;
		
		
		//select * from challenge where idChallenge=;
	}

}
