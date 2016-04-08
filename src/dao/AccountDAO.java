package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;


import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.AccountDAOInterface;
import models.Account;
import java.sql.*;
public class AccountDAO implements AccountDAOInterface {
	
	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		
		 try
    		{
		String query = " insert into account (idAccount,username,password,profilepic,name,timeCreated,birthDate)"
	       			 + " values (?,?, ?, ?, ?, ?,?)";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		preparedStmt.setInt(1,0);
		preparedStmt.setString(2,account.getUsername());
	    preparedStmt.setString(3,account.getPassword());
	    preparedStmt.setString(4, account.getProfilePic());
      	preparedStmt.setString(5,account.getName());
      	preparedStmt.setString(6,currentTime);
      	preparedStmt.setDate(7,account.getBirthDate());
		
		preparedStmt.execute();
		preparedStmt.close();
		con.close();		
		
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;				
		}
 		return true;

		//insert into Account
		//values (username,password,pic,name,timeCreated,birthDate);
		
		//return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		
		//Update	Account
		//Set	username=,password=,pic=,name=,birthday=,
		//where	username=(account.username);
		try
    		{
		
//		String query = " Update account set username= ?,password=?,profilepic=?,name=?,birthDate=?"
//       			 + " Where idAccount=?";
		String query = " Update account set username=ifnull(?,username), password=ifnull(?,password), profilepic=ifnull(?,profilepic)"
						+ ", name=ifnull(?,name), birthDate=ifnull(?,birthDate) Where idAccount=?";

		Connection con = JDBCMySQLConnection.getConnection();
					
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		
		//if(account.getUsername() != null)
			preparedStmt.setString(1,account.getUsername());
	    //if(account.getPassword() != null)
	    	preparedStmt.setString(2,account.getPassword());
	    //if(account.getProfilePic() != null)
	    	preparedStmt.setString(3, account.getProfilePic());  	
	    //if(account.getName() != null)
	    	preparedStmt.setString(4,account.getName());
	    //if(account.getBirthDate() != null)	
	    	preparedStmt.setDate(5,account.getBirthDate());
	    
	    preparedStmt.setInt	(6,account.getId());

		preparedStmt.execute();
		preparedStmt.close();
		con.close();	
		
    	}
		catch(SQLException e){
			e.printStackTrace();
			return false;				
		}
		return true;
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
		//delete from Account
		//where idAccount=(		Select idAccount
		//from Account
		//where username = account.username and password=account.password;)

		try{
		String query = " Delete from account Where idAccount=?";
		Connection con = JDBCMySQLConnection.getConnection();
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1,account.getId());

		preparedStmt.execute();
		preparedStmt.close();
		con.close();		
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;				
		}	
		return true;
	}

	@Override
	public Account getAccountByID(int id) {
		// TODO Auto-generated method stub
		//Select idAccount
		//from	Account
		//where	idAccount=id;
		
		Account topass=new Account();

		try {
			String query = "SELECT * FROM Account WHERE idAccount=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, id);
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {

				topass.setId(temp.getInt("idAccount"));
				topass.setUsername(temp.getString("username"));
				topass.setPassword(temp.getString("password"));
				topass.setProfilePic(temp.getString("profilepic"));
				topass.setName(temp.getString("name"));
				

				topass.setTimeCreated(temp.getDate("timeCreated"));
				topass.setBirthDate(temp.getDate("birthDate"));	
			}
			
			preparedStmt.close();
			con.close();
		} 
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return topass;
	}

	@Override
	public ArrayList<Account> getAccountsByName(String name) {
		// TODO Auto-generated method stub
		//Select *
		//from Account
		//Order by name ASC;
		//return null;
		ArrayList<Account> accountlist= new ArrayList<Account>();
		ArrayList<Integer> idlist= new ArrayList<>();
		ArrayList<String> usernamelist=new ArrayList<String>();
		ArrayList<String> passwordlist=new ArrayList<String>();
		ArrayList<String> profilepiclist=new ArrayList<String>();
		ArrayList<String> namelist=new ArrayList<String>();
		ArrayList<Date> timecreatedlist=new ArrayList<Date>();
		ArrayList<Date> birthdatelist=new ArrayList<Date>();
		
		String query = "SELECT * FROM account where name=?";
		
		try 
		{
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,name);
			ResultSet temp = preparedStmt.executeQuery();
			
			while (temp.next()) {
			idlist.add(temp.getInt("idAccount"));
			usernamelist.add(temp.getString("username"));
			passwordlist.add(temp.getString("password"));
			profilepiclist.add(temp.getString("profilepic"));
			namelist.add(temp.getString("name"));
			timecreatedlist.add(temp.getDate("timeCreated"));
			birthdatelist.add(temp.getDate("birthDate"));
			}
			for(int i=0; i<idlist.size();i++)
			{
				Account topass=new Account();
				topass.setId(idlist.get(i));
				topass.setUsername(usernamelist.get(i));
				topass.setPassword(passwordlist.get(i));
				topass.setProfilePic(profilepiclist.get(i));
				topass.setName(namelist.get(i));
				topass.setTimeCreated(timecreatedlist.get(i));
				topass.setBirthDate(birthdatelist.get(i));
				accountlist.add(topass);
			}
			
			preparedStmt.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return accountlist;

	}

	public boolean checkLoginDetails(String username, String password){
		//SELECT * from account where username="wilbertluy11" and password="1234";
		Account topass=new Account();
		try{
		
		String query = "SELECT username,password from account where username=? and password=?";

		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1,username);
		preparedStmt.setString(2,password);
		
		ResultSet temp = preparedStmt.executeQuery();

			if(!temp.next()) {
				preparedStmt.close();
				con.close();
				return false;
			}
			else {
				preparedStmt.close();
				con.close();
				return true;
			}
			/*
			while (temp.next()) {
				topass.setUsername(temp.getString("username"));
				topass.setPassword(temp.getString("password"));
						
				}
			
			preparedStmt.close();
			con.close();
			
			if(topass.getUsername().equals(username) && topass.getPassword().equals(password))
				return true;
			else
				return false;
			*/
    	}
		catch(SQLException e)
		{
			//System.out.println("chcklogindetails error");		
			e.printStackTrace();
			return false;
		}
	}
	
	public Account getAccountByUsername(String username){
	
		Account topass=new Account();
		try
    	{
		String query = "SELECT * from account where username=?";
		Connection con = JDBCMySQLConnection.getConnection();
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1,username);

		ResultSet temp = preparedStmt.executeQuery();
		while (temp.next()) {
			topass.setId(temp.getInt("idAccount"));
			topass.setUsername(temp.getString("username"));
			topass.setPassword(temp.getString("password"));
			topass.setProfilePic(temp.getString("profilepic"));
			topass.setBirthDate(temp.getDate("birthDate"));
			topass.setTimeCreated(temp.getDate("timeCreated"));
			topass.setName(temp.getString("name"));
			//con.close();		
			}

		preparedStmt.close();
		con.close();
    	}
		catch(SQLException e)
		{
			//System.out.println("chcklogindetails error");
			e.printStackTrace();
			return null;
		}
		return topass;
		
	}

	@Override
	public boolean checkIfUsernameExists(String username) {
		// TODO Auto-generated method stub
		try
    	{
			String query = "SELECT username from account where username=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,username);
	
			ResultSet temp = preparedStmt.executeQuery();
			if(!temp.next()) {
				preparedStmt.close();
				con.close();
				return false;
			}
			else {
				preparedStmt.close();
				con.close();
				return true;
			}
    	}
		catch(SQLException e)
		{
			//System.out.println("chcklogindetails error");
			e.printStackTrace();
			return true;
		}
	}
}
