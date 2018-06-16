package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.security.provider.MD5;
import sun.security.rsa.RSASignature.MD5withRSA;

import db.DBHelper;

public class ManagerControl {
	public Manager queryManager(Manager manager){
		Manager manager1=new Manager();
		String sql="select managerName,managerPassword from tb_manager where managerName=?";
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, manager.getManagerName());
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()){
				manager1.setManagerName(rs.getString("managerName"));
				manager1.setManagerPassword(rs.getString("managerPassword"));
			}
			if(manager1.getManagerPassword()!=null){
				/*if(security.MD5.MD5(manager.getManagerPassword()).equals(manager1.getManagerPassword())){
					return manager1;
				}
				else{
					return null;
				}*/
				return manager1;
			}
			else{
				return null;//用户名不存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int insertManager(Manager manager) {
		String sql="INSERT INTO tb_manager (managerName,managerPassword) VALUES " +
				"(?,?);";
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, manager.getManagerName());
			preparedStatement.setString(2, security.MD5.MD5(manager.getManagerPassword()));
			boolean rs=preparedStatement.execute();
			if(!rs){
				return 0;//插入成功
			}
			else {
				return 1;//插入失败
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public int updateManager(Manager manager) {
		String sql="UPDATE tb_manager SET managerPassword=? where managerName=?";
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, security.MD5.MD5(manager.getManagerPassword()));
			preparedStatement.setString(2, manager.getManagerName());
			boolean rs=preparedStatement.execute();
			if(!rs){
				return 0;//插入成功
			}
			else {
				return 1;//插入失败
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public static void main(String[] args) {
		Manager manager=new Manager();
		ManagerControl managerControl=new ManagerControl();
		manager.setManagerName("李宣佚");
		manager.setManagerPassword("lxy991120");
		System.out.println(managerControl.insertManager(manager));
		//System.out.println(managerControl.queryManager(manager).getManagerName());
		//System.out.println(managerControl.queryManager(manager).getManagerPassword());
		//System.out.println(managerControl.updateManager(manager));
	}
}
