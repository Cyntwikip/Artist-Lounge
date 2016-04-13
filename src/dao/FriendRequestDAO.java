package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.FriendRequestDAOInterface;
import models.FriendRequest;

public class FriendRequestDAO implements FriendRequestDAOInterface {
	Connection con = JDBCMySQLConnection.getConnection();
	@Override
	public boolean addRequest(FriendRequest request) {
		// TODO Auto-generated method stub
		try{
			String query="insert into friendrequest values(?,?,?,?)";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,request.getSenderID());
			preparedStmt.setInt(2,request.getReceiverID());
			preparedStmt.setBoolean(3, request.isPending());
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			preparedStmt.setString(4,currentTime);
			
			preparedStmt.execute();
			preparedStmt.close();
			con.close();		
			
		}
		catch(SQLException e)
		{
			return false;				
		}
	 		return true;
		//insert into friendrequest values(?,?,?,?);
	}

	@Override
	public boolean updateRequest(FriendRequest request) {
		// TODO Auto-generated method stub
		try{
			String query="update friendrequest set pending=ifnull(?,pending),time=ifnull(?,time) where senderid=? and receiverid=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setBoolean(1, request.isPending());
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			preparedStmt.setString(2,currentTime);
			preparedStmt.setInt(3, request.getSenderID());
			preparedStmt.setInt(4,request.getReceiverID());
			preparedStmt.execute();
			preparedStmt.close();
			con.close();		
		}
		catch(SQLException e)
		{
			return false;
		}
			return true;
		
		
		//update friendrequest set(pending=?,time=?);
		
	}

	@Override
	public boolean deleteRequest(FriendRequest request) {
		// TODO Auto-generated method stub
		try{
			String query="delete from friendrequest where senderid=? and receiverid=?";
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
			return false;
		}
			return true;

		//delete from friendrequest where senderid=? and receiverid=?;
	}

	@Override
	public ArrayList<FriendRequest> getRequestsBySender(int senderID) {
		// TODO Auto-generated method stub
		ArrayList<FriendRequest> friendlist=new ArrayList<FriendRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiverid=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		try{
			String query="select * from friendrequest where senderid=? order by receiverid ASC";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,senderID);
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {
				senderidlist.add(temp.getInt("senderid"));
				receiverid.add(temp.getInt("receiverid"));
				pendinglist.add(temp.getBoolean("pending"));
				timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
				FriendRequest t1=new FriendRequest();
				t1.setSenderID(senderidlist.get(i));
				t1.setReceiverID(receiverid.get(i));
				t1.setPending(pendinglist.get(i));
				t1.setTime(timelist.get(i));
				
				friendlist.add(t1);
				
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println("error on getrequest by sender");
			return null;
		}

		return friendlist;
		//select * from friendrequest where senderID=? order by receiverid ASC;
	}

	@Override
	public ArrayList<FriendRequest> getRequestsByReceiver(int receiverID) {
		// TODO Auto-generated method stub
		ArrayList<FriendRequest> friendlist=new ArrayList<FriendRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiverid=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		try{
			String query="select * from friendrequest where receiverid=? order by senderid ASC";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,receiverID);
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {
				senderidlist.add(temp.getInt("senderid"));
				receiverid.add(temp.getInt("receiverid"));
				pendinglist.add(temp.getBoolean("pending"));
				timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
				FriendRequest t1=new FriendRequest();
				t1.setSenderID(senderidlist.get(i));
				t1.setReceiverID(receiverid.get(i));
				t1.setPending(pendinglist.get(i));
				t1.setTime(timelist.get(i));
				
				friendlist.add(t1);
				
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println("error on getrequest by sender");
			return null;
		}

		return friendlist;
	}
	
	//added by CJ
	
	@Override
	public ArrayList<FriendRequest> getRequestsBySenderAndReceiver(int senderID, int receiverID) {
		// TODO Auto-generated method stub
		ArrayList<FriendRequest> friendlist=new ArrayList<FriendRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiverid=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		try{
			String query="select * from friendrequest where senderid=? and receiverid=? and pending=0 order by `time` ASC";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,senderID);
			preparedStmt.setInt(2, receiverID);
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {
				senderidlist.add(temp.getInt("senderid"));
				receiverid.add(temp.getInt("receiverid"));
				pendinglist.add(temp.getBoolean("pending"));
				timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
				FriendRequest t1=new FriendRequest();
				t1.setSenderID(senderidlist.get(i));
				t1.setReceiverID(receiverid.get(i));
				t1.setPending(pendinglist.get(i));
				t1.setTime(timelist.get(i));
				
				friendlist.add(t1);
				
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("error on getrequest by sender");
			return null;
		}

		return friendlist;
		//select * from friendrequest where senderID=? order by receiverid ASC;
	}

	@Override
	public ArrayList<FriendRequest> getRequestsByReceiver(int receiverID, int pending) {
		// TODO Auto-generated method stub
		ArrayList<FriendRequest> friendlist=new ArrayList<FriendRequest>();
		ArrayList<Integer> senderidlist=new ArrayList<>();
		ArrayList<Integer> receiverid=new ArrayList<>();
		ArrayList<Boolean> pendinglist=new ArrayList<Boolean>();
		ArrayList<Date> timelist=new ArrayList<Date>();
		try{
			String query="select * from friendrequest where receiverid=? and pending=? order by senderid ASC";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,receiverID);
			preparedStmt.setInt(2,pending);
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {
				senderidlist.add(temp.getInt("senderid"));
				receiverid.add(temp.getInt("receiverid"));
				pendinglist.add(temp.getBoolean("pending"));
				timelist.add(temp.getDate("time"));
			}
			for(int i=0;i<senderidlist.size();i++)
			{
				FriendRequest t1=new FriendRequest();
				t1.setSenderID(senderidlist.get(i));
				t1.setReceiverID(receiverid.get(i));
				t1.setPending(pendinglist.get(i));
				t1.setTime(timelist.get(i));
				
				friendlist.add(t1);
				
			}
			preparedStmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println("error on getrequest by sender");
			return null;
		}

		return friendlist;
	}

}
