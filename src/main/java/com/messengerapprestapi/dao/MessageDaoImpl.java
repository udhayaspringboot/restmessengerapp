package com.messengerapprestapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.messengerapprestapi.model.Message;

public class MessageDaoImpl implements MessageDao {

	
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
	public int saveMessage(Message message) {
		getConnectionMySql();
		//Date dgh = new Date(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today;
		try {
			today = dateFormat.parse(dateFormat.format(new Date()));
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			String query="insert into message values (?,?,?,?)";
			try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, message.getMessageId());
			ps.setString(2, message.getMessage());
			
				ps.setDate(3, sqlDate);
				ps.setString(4, message.getAuthor());
				ps.executeUpdate();
				System.out.println("Message created");
				
				
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
	public List<Message> getAllMessages() {
		
		
		getConnectionMySql();
		List<Message> ldept =new ArrayList<>();
		String query ="select * from message";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
				int id =rs.getInt(1);
				String name = rs.getString(2);
				Date da = rs.getDate(3);
				String author =rs.getString(4);
				Message dtd = new Message(id,name,da,author);
				ldept.add(dtd);
				
			}
			System.out.println("All Data From Message");
			//con.close();
			
		} catch (SQLException e) {
			System.err.println("Data may not present");		}

		return ldept;
	}

	@Override
	public int deleteMessage(int messId) {
		getConnectionMySql();
		String query ="delete from message where message_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, messId);
			ps.executeUpdate();
			
			System.out.println("Data deleted from message");
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("data may not present");
		}
		
		return 0;
	}



	@Override
	public Message getMessage(int messId) {
		getConnectionMySql();
		Message dtd = null;
		String query ="select * from message where message_id=?";
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
			 dtd = new Message(id,name,da,author);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtd;
	}



	@Override
	public int updateMessage(Message message) {
		getConnectionMySql();
		java.sql.Date sqlDate = new java.sql.Date(message.getCreated().getTime());
		String query ="update message set message=?,date=?,author=? where message_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, message.getMessage());
			ps.setDate(2, sqlDate);
			ps.setString(3, message.getAuthor());
			ps.setInt(4, message.getMessageId());
			ps.executeUpdate();
			//con.close();
			System.out.println("Message table updated");
			
		} catch (SQLException e) {
		
			
			System.err.println("Data not present");
		}

		
		return 0;
	}

}
