package model;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

import db.DBHelper;

public class UserControl {
	public void addUser(User us) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String sqString = "insert into user (name,password)" + "values("
				+ "?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sqString);
		ptmt.setString(1, us.getNameString());
		ptmt.setString(2, us.getPassword());
		ptmt.execute();
	}

	public void updateUser(User us) throws SQLException{
		Connection conn=DBHelper.getConnection();
		String sql="update user"+" set name=?,password=?"+" where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,us.getNameString() );
		ptmt.setString(2, us.getPassword());
		ptmt.setInt(3, us.getId());
		ptmt.execute();
	}
	public void  delUser(Integer id) throws SQLException {
		Connection conn=DBHelper.getConnection();
		String sql="delete from user where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	public User queryUserId(User user){
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			String sql="select id from user where name=?";
			PreparedStatement ptmt=connection.prepareStatement(sql);
			ptmt.setString(1, user.getNameString());
			ResultSet rs=ptmt.executeQuery();
			if(rs.next()){
				user.setId(Integer.parseInt(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public java.util.List<User> query(String name) throws SQLException {
		Connection conn = DBHelper.getConnection();
//		java.sql.Statement stmt = conn.createStatement();
		String sql="select * from user where name=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//ResultSet rs = stmt.executeQuery("select * from user");
		ptmt.setString(1, name);
		ResultSet rs=ptmt.executeQuery();
		User user = null;
		java.util.List<User> usList = new ArrayList<User>();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setNameString(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			usList.add(user);
		}
		return usList;
	}
	public static void main(String args[]) throws SQLException {
		UserControl ucControl = new UserControl();
		java.util.List<User> usList = ucControl.query("ÂíÎÄ¿­");
		for (User user : usList) {
			System.out.println(user.getId() + "," + user.getNameString() + ","
					+ user.getPassword());
		}
		
	}
}
