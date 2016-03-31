package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.theopentutorials.jdbc.db.JDBCMySQLConnection;

import dao_interface.CommentDAOInterface;
import models.Comment;

public class CommentDAO implements CommentDAOInterface {
	
	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		 try
 		{
			String query = "insert into comment values(?,?,?,?,?)";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,0);
			preparedStmt.setInt(2,comment.getAccountID());
			preparedStmt.setInt(3,comment.getPostID());
			preparedStmt.setString(4,comment.getComment());
			
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			preparedStmt.setString(5,currentTime);
			
			preparedStmt.execute();
			con.close();	
 		}
		catch(SQLException e)
		{
			return false;
		}
		 	return true;
	
		//insert into comment values(0,?,?,?,?);
	}

	@Override
	public boolean deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		try
		{
			String query="delete from comment where idComment=? and Account_idAccount=?";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,comment.getId());
			preparedStmt.setInt(2,comment.getAccountID());
			
			preparedStmt.execute();
			con.close();	
		
		}
		catch(SQLException e)
		{
			return false;
		}
		return true;
		
		//delete * from comment where idComment=?,Account_idAccount=?;
	}

	@Override
	public ArrayList<Comment> getCommentsByAccount(int accountID) {
		// TODO Auto-generated method stub
		ArrayList<Comment> commentlist=new ArrayList<Comment>();
		ArrayList<Integer> idcommentlist=new ArrayList<>();
		ArrayList<Integer> account_idaccountlist=new ArrayList<>();
		ArrayList<Integer> post_idpostlist=new ArrayList<>();
		ArrayList<String> comlist=new ArrayList<String>();
		ArrayList<Date>	timelist=new ArrayList<Date>();
		
		try{
			String query="Select * from comment where Account_idAccount=? order by Post_idPost ASC";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,accountID);
			
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {
				idcommentlist.add(temp.getInt("idComment"));
				account_idaccountlist.add(temp.getInt("Account_idAccount"));
				post_idpostlist.add(temp.getInt("Post_idPost"));
				comlist.add(temp.getString("comment"));
				timelist.add(temp.getDate("time"));
				for(int i=0;i<idcommentlist.size();i++)
				{
					Comment t1=new Comment();
					t1.setId(idcommentlist.get(i));
					t1.setAccountID(account_idaccountlist.get(i));
					t1.setPostID(post_idpostlist.get(i));
					t1.setComment(comlist.get(i));
					t1.setTime(timelist.get(i));
					
					commentlist.add(t1);
					
				}
			}
				
				
		}
		catch(SQLException e)
		{
			System.out.println("error on getcomments by account");
			return null;
			
		}
		return commentlist;
		
		
		
	}

	@Override
	public ArrayList<Comment> getCommentsByPost(int postID) {
		// TODO Auto-generated method stub
		ArrayList<Comment> commentlist=new ArrayList<Comment>();
		ArrayList<Integer> idcommentlist=new ArrayList<>();
		ArrayList<Integer> account_idaccountlist=new ArrayList<>();
		ArrayList<Integer> post_idpostlist=new ArrayList<>();
		ArrayList<String> comlist=new ArrayList<String>();
		ArrayList<Date>	timelist=new ArrayList<Date>();
		
		try{
			String query="Select * from comment where Post_idPost=? order by Account_idAccount ASC";
			Connection con = JDBCMySQLConnection.getConnection();
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1,postID);
			
			
			ResultSet temp = preparedStmt.executeQuery();

			while (temp.next()) {
				idcommentlist.add(temp.getInt("idComment"));
				account_idaccountlist.add(temp.getInt("Account_idAccount"));
				post_idpostlist.add(temp.getInt("Post_idPost"));
				comlist.add(temp.getString("comment"));
				timelist.add(temp.getDate("time"));
				for(int i=0;i<idcommentlist.size();i++)
				{
					Comment t1=new Comment();
					t1.setId(idcommentlist.get(i));
					t1.setAccountID(account_idaccountlist.get(i));
					t1.setPostID(post_idpostlist.get(i));
					t1.setComment(comlist.get(i));
					t1.setTime(timelist.get(i));
					
					commentlist.add(t1);
					
				}
			}
				
				
		}
		catch(SQLException e)
		{
			System.out.println("error on getcomments by post");
			return null;
			
		}
		return commentlist;
		
		
		
		
		
		
	}

}
