package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.ChallengeRequestDAOInterface;

import models.ChallengeRequest;

public class ChallengeRequestDAO implements ChallengeRequestDAOInterface {
	
	@Override
	public boolean addRequest(ChallengeRequest request) {
		// TODO Auto-generated method stub
		try
		{
		String query = " insert into challengerequest (senderid,receiverid,pending,time) values(?,?,?,?)";
	
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,request.getSenderID());
		preparedStmt.setInt(2,request.getReceiverID());
		preparedStmt.setBoolean(3,request.isPending());
		preparedStmt.setDate(4,request.getTime());
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
		
		//insert into challengerequest values(,,,"");
	
		
	}

	@Override
	public boolean updateRequest(ChallengeRequest request) {
		// TODO Auto-generated method stub
		try
		{
		String query = " update challengerequest set pending=ifnull(?,pending),time=ifnull(?,time) where senderid=? and receiverid=?";
		

		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setBoolean(1,request.isPending());
		preparedStmt.setDate(2,request.getTime());
		
		preparedStmt.setInt(3,request.getSenderID());
		preparedStmt.setInt(4,request.getReceiverID());
		
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
		//update challengerequest set pending=,time=, where senderid=,receiverid=;
	}

	@Override
	public boolean deleteRequest(ChallengeRequest request) {
		// TODO Auto-generated method stub
		try
		{
		String query = " delete from challengerequest where senderid=? and receiverid=?";
		

		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,request.getSenderID());
		preparedStmt.setInt(2,request.getReceiverID());
		
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
		
		
		
		//delete from challengerequest where senderid=,receiverid=;
	}

	@Override
	public ArrayList<ChallengeRequest> getRequestsBySender(int senderID) {
		// TODO Auto-generated method stub
		ArrayList<ChallengeRequest> challengelist= new ArrayList<ChallengeRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiveridlist=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		
		try
		{
		String query = "select * from challengerequest where senderid=? order by (receiverid) ASC";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,senderID);	
		ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			senderidlist.add(temp.getInt("senderid"));
			receiveridlist.add(temp.getInt("receiverid"));
			pendinglist.add(temp.getBoolean("pending"));
			timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
			ChallengeRequest t1=new ChallengeRequest();
			t1.setSenderID(senderidlist.get(i));
			t1.setReceiverID(receiveridlist.get(i));
			t1.setPending(pendinglist.get(i));
			t1.setTime(timelist.get(i));
			challengelist.add(t1);
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return challengelist;
		//select * from challengerequest where senderid= order by (receiverid) ASC;
	}
	@Override
	public ArrayList<ChallengeRequest> getRequestsByReceiver(int receiverID) {
		// TODO Auto-generated method stub
		
		
		ArrayList<ChallengeRequest> challengelist= new ArrayList<ChallengeRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiveridlist=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		
		try
		{
		String query = "select * from challengerequest where receiverid=? order by (senderid) ASC";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,receiverID);	
		ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			senderidlist.add(temp.getInt("senderid"));
			receiveridlist.add(temp.getInt("receiverid"));
			pendinglist.add(temp.getBoolean("pending"));
			timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
			ChallengeRequest t1=new ChallengeRequest();
			t1.setSenderID(senderidlist.get(i));
			t1.setReceiverID(receiveridlist.get(i));
			t1.setPending(pendinglist.get(i));
			t1.setTime(timelist.get(i));
			challengelist.add(t1);
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
			
		}
		return challengelist;
	}
	
	//added by CJ
	@Override
	public ArrayList<ChallengeRequest> getRequestsBySenderAndReceiver(int senderID, int receiverID) {
		// TODO Auto-generated method stub
		ArrayList<ChallengeRequest> challengelist= new ArrayList<ChallengeRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiveridlist=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		
		try
		{
		String query = "select * from challengerequest where senderid=? and receiverid=? order by `time` ASC";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,senderID);
		preparedStmt.setInt(2,receiverID);
		ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			senderidlist.add(temp.getInt("senderid"));
			receiveridlist.add(temp.getInt("receiverid"));
			pendinglist.add(temp.getBoolean("pending"));
			timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
			ChallengeRequest t1=new ChallengeRequest();
			t1.setSenderID(senderidlist.get(i));
			t1.setReceiverID(receiveridlist.get(i));
			t1.setPending(pendinglist.get(i));
			t1.setTime(timelist.get(i));
			challengelist.add(t1);
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return challengelist;
		//select * from challengerequest where senderid= order by (receiverid) ASC;
	}

	@Override
	public ArrayList<ChallengeRequest> getRequestsByReceiver(int receiverID, int pending) {
		// TODO Auto-generated method stub
		
		
		ArrayList<ChallengeRequest> challengelist= new ArrayList<ChallengeRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiveridlist=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		
		try
		{
		String query = "select * from challengerequest where receiverid=? and pending=? order by (senderid) ASC";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,receiverID);	
		preparedStmt.setInt(2,pending);
		ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			senderidlist.add(temp.getInt("senderid"));
			receiveridlist.add(temp.getInt("receiverid"));
			pendinglist.add(temp.getBoolean("pending"));
			timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
			ChallengeRequest t1=new ChallengeRequest();
			t1.setSenderID(senderidlist.get(i));
			t1.setReceiverID(receiveridlist.get(i));
			t1.setPending(pendinglist.get(i));
			t1.setTime(timelist.get(i));
			challengelist.add(t1);
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
			
		}
		return challengelist;
	}

}
