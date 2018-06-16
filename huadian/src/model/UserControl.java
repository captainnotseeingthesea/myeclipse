package model;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import security.MD5;

import com.mysql.jdbc.Statement;

import db.DBHelper;

public class UserControl {
	public void addUser(User us) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String sqString = "insert into user (name,password)" + "values("
				+ "?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sqString);
		ptmt.setString(1, us.getNameString());
		ptmt.setString(2, MD5.MD5(us.getPassword()));
		ptmt.execute();
	}

	public boolean updateUser(User user) throws SQLException{
		Connection conn=DBHelper.getConnection();
		String sql="UPDATE user set name=?,password=?  WHERE id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,user.getNameString() );
		ptmt.setString(2, MD5.MD5(user.getPassword()));
		ptmt.setInt(3, user.getId());
		return ptmt.execute();
	}
	
	public boolean updateUserLoginTime(User user) throws SQLException{
		Connection conn=DBHelper.getConnection();
		String sql="UPDATE user set loginTime=?  WHERE id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,user.getLoginTime() );
		ptmt.setInt(2,user.getId());
		return ptmt.execute();
	}
	
	public void  delUser(Integer id) throws SQLException {
		Connection conn=DBHelper.getConnection();
		String sql="delete from user where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	
	public User queryUser(User user){
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			String sql="select * from user where name=?";
			PreparedStatement ptmt=connection.prepareStatement(sql);
			ptmt.setString(1,user.getNameString());
			ResultSet rs=ptmt.executeQuery();
			User sel_user=new User();
			if(rs.next()){
				sel_user.setId(Integer.parseInt(rs.getString("id")));
				sel_user.setNameString(rs.getString("name"));
				sel_user.setPassword(rs.getString("password"));
				sel_user.setLoginTime(rs.getString("loginTime"));
				return sel_user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public java.util.List<User> query(String name) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String sql="select * from user where name=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, name);
		ResultSet rs=ptmt.executeQuery();
		User user = null;
		java.util.List<User> usList = new ArrayList<User>();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setNameString(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLoginTime(rs.getString("loginTime"));
			usList.add(user);
		}
		return usList;
	}
	
	public int queryCount() throws SQLException{
		Connection conn=DBHelper.getConnection();
		int count=0;
		String sql="select count(*) from user";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			count=rs.getInt(1);
		}
		return count;
	}
	
	public ArrayList<User> queryAll(PageBean<User> pageBean) throws SQLException {
		Connection conn = DBHelper.getConnection();
		int integer=1;//用于计数
		String sql="select * from user limit ?,?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, pageBean.getStartIndex());
		ptmt.setInt(2, pageBean.getPageSize());
		ResultSet rs=ptmt.executeQuery();
		User user = null;
		ArrayList<User> usList = new ArrayList<User>();
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setNameString(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLoginTime(rs.getString("loginTime"));
			user.setKid(integer++);
			usList.add(user);
		}
		return usList;
	}
	
	
	public static void main(String args[]) throws SQLException {
		UserControl ucControl = new UserControl();
		PageBean<User> pageBean=new PageBean<User>(1,10,ucControl.queryCount());
		java.util.List<User> usList = ucControl.queryAll(pageBean);
		for (User user : usList) {
			System.out.println(user.getId() + "," + user.getNameString() + ","
					+ user.getPassword());
		}
	}
}
