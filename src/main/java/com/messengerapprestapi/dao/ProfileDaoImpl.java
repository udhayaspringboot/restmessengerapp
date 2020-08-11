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

import com.messengerapprestapi.model.Message;
import com.messengerapprestapi.model.Profile;

public class ProfileDaoImpl implements ProfileDao {

	
	
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
	public int savProfile(Profile profile) {
		getConnectionMySql();
		//Date dgh = new Date(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today;
		try {
			today = dateFormat.parse(dateFormat.format(new Date()));
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			String query="insert into profile values (?,?,?,?,?)";
			try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, profile.getProfileId());
			ps.setString(2,profile.getProfileName());
			ps.setString(3,profile.getFirstName());
			ps.setString(4,profile.getLastName());
				ps.setDate(5, sqlDate);
				
				ps.executeUpdate();
				System.out.println("Profile created");
				
				
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
	public List<Profile> getAllProfiles() {

		getConnectionMySql();
		List<Profile> ldept =new ArrayList<>();
		String query ="select * from profile";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
				int id =rs.getInt(1);
				String profName = rs.getString(2);
				String firstName = rs.getString(3);
				String lastName = rs.getString(4);
				Date da = rs.getDate(5);
				Profile dtd = new Profile(id,profName,firstName,lastName,da);
				ldept.add(dtd);
				
			}
			System.out.println("All Data From Profile");
			//con.close();
			
		} catch (SQLException e) {
			System.err.println("Data may not present");		}

		return ldept;
	}

	@Override
	public Profile getProfile(String profName) {
		getConnectionMySql();
		Profile dtd = null;
		String query ="select * from profile where profile_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, profName);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
				int id =rs.getInt(1);
				String profNamee = rs.getString(2);
				String firstName = rs.getString(3);
				String lastName = rs.getString(4);
				Date da = rs.getDate(5);
				//String author =rs.getString(5);
			 dtd = new Profile(id,profNamee,firstName,lastName,da);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtd;
	}

	@Override
	public int updateProfile(Profile profile) {
		getConnectionMySql();
		java.sql.Date sqlDate = new java.sql.Date(profile.getCreated().getTime());
		String query ="update profile set profile_id=?,first_name=?,last_name=?,created=? where profile_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,  profile.getProfileId());
			ps.setString(2, profile.getFirstName());
			ps.setString(3, profile.getLastName());
			ps.setDate(4, sqlDate);
			ps.setString(5, profile.getProfileName());
			ps.executeUpdate();
			//con.close();
			System.out.println("Profile table updated");
			
		} catch (SQLException e) {
		
			
			System.err.println("Data not present");
		}

		
		return 0;
	}

	@Override
	public int deleteProfile(String  profName) {
		getConnectionMySql();
		String query ="delete from profile where profile_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, profName);
			ps.executeUpdate();
			
			System.out.println("Data deleted from profile");
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("data may not present");
		}
		
		return 0;
	}

}
