package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBHelper;

public class ClassControl {
	public GoodsClass queryClassName(Goods goods){
		GoodsClass goodsClass=new GoodsClass();
		String sql="select * from tb_class where classId=?";
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, goods.getClassId());
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()){
				goodsClass.setClassName(rs.getString("className"));
				goodsClass.setClassId(goods.getClassId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodsClass;
		
	}
}
