package com.messengerapprestapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.messengerapprestapi.model.Comment;

public class CommentdaoImpl implements CommentDao{

	
	static Connection con;
	//connection with mysql
	static void getConnectionMySql()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}  
		try {
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/messdb","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}  
	}
	
	
	@Override
	public List<Comment> getComms(int messId) {
		getConnectionMySql();
		
		List<Comment> comm = new ArrayList<>();
		Comment dtd = null;
		String query ="select * from comment where com_mes=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, messId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
				int id =rs.getInt(1);
				String name = rs.getString(2);
				Date da = rs.getDate(3);
				String author =rs.getString(4);
				int comMesFk= rs.getInt(5);
			 dtd = new Comment(id,name,da,author,comMesFk);
				comm.add(dtd);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comm;
		
		
		
	}

	@Override
	public int saveComms(Comment comment) {
		getConnectionMySql();
		//Date dgh = new Date(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today;
		try {
			today = dateFormat.parse(dateFormat.format(new Date()));
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			String query="insert into comment values (?,?,?,?,?)";
			try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, comment.getCommentId());
			ps.setString(2, comment.getComMessage());
			
				ps.setDate(3, sqlDate);
				ps.setString(4,comment.getComAuthor());
				ps.setInt(5, comment.getComMesFk());
				ps.executeUpdate();
				System.out.println("Comment created");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateComms(Comment comment) {
		
		getConnectionMySql();
		java.sql.Date sqlDate = new java.sql.Date(comment.getComCreated().getTime());
		String query ="update comment set com_message=?,com_date=?,com_author=? where comment_id=? and com_mes=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,comment.getComMessage());
			ps.setDate(2, sqlDate);
			ps.setString(3, comment.getComAuthor());
			ps.setInt(4, comment.getCommentId());
			ps.setInt(5, comment.getComMesFk());
			ps.executeUpdate();
			//con.close();
			System.out.println("Message table updated");
			
		} catch (SQLException e) {
		
			
			System.err.println("Data not present");
		}

		
		
		return 0;
	}


	@Override
	public int deleComms(int messId, int commId) {
		getConnectionMySql();
		String query ="delete from comment where comment_id=? and com_mes=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, commId);
			ps.setInt(2,messId);
			ps.executeUpdate();
			
			System.out.println("Data deleted from Comment");
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("data may not present");
		}
		
		return 0;
	}

}
