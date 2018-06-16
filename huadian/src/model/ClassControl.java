package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public int queryCount(){
		GoodsClass goodsClass=new GoodsClass();
		String sql="select count(*) from tb_class";
		Connection connection;
		int count=0;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	}
	
	public ArrayList<GoodsClass> queryAll(PageBean<GoodsClass> pagebean){
		ArrayList<GoodsClass> list=new ArrayList<GoodsClass>();
		Integer integer=1;
		String sql="select * from tb_class limit ?,?";
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, pagebean.getStartIndex());
			preparedStatement.setInt(2, pagebean.getPageSize());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				GoodsClass goodsClass=new GoodsClass();
				goodsClass.setClassId(rs.getInt("classId"));
				goodsClass.setClassName(rs.getString("className"));
				goodsClass.setKid(integer++);
				list.add(goodsClass);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void addGoodsClass(GoodsClass goodsClass) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String sqString = "insert into tb_class (className) values(?)";
		PreparedStatement ptmt = conn.prepareStatement(sqString);
		ptmt.setString(1, goodsClass.getClassName());
		ptmt.execute();
	}
	
	public void  delGoodsClass(GoodsClass goodsClass) throws SQLException {
		Connection conn=DBHelper.getConnection();
		String sql="delete from tb_class where classId=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, goodsClass.getClassId());
		ptmt.execute();
	}
	
	public boolean updateGoodsClass(GoodsClass goodsClass) throws SQLException{
		Connection conn=DBHelper.getConnection();
		String sql="UPDATE tb_class set className =? WHERE classId=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,goodsClass.getClassName() );
		ptmt.setInt(2, goodsClass.getClassId());
		return ptmt.execute();
	}
	
	public GoodsClass queryGoodsClass(GoodsClass goodsClass) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String sqString = "SELECT * FROM tb_class WHERE className=?";
		PreparedStatement ptmt = conn.prepareStatement(sqString);
		ptmt.setString(1, goodsClass.getClassName());
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			GoodsClass goodsClass2=new GoodsClass();
			goodsClass2.setClassId(rs.getInt("classId"));
			goodsClass2.setClassName(rs.getString("className"));
			return goodsClass2;
		}
		return null;
	}
	
	public static void main(String[] args) {
		ClassControl classControl=new ClassControl();
		/*PageBean<GoodsClass> pageBean=new PageBean<GoodsClass>(1,10,6);
		ArrayList<GoodsClass> list=classControl.queryAll(pageBean);
		for(GoodsClass goodsClass:list){
			System.out.println(goodsClass.getClassName());
		}*/
		GoodsClass goodsClass=new GoodsClass();
		goodsClass.setClassName("¶ú»ú");
		try {
			System.out.println(classControl.queryGoodsClass(goodsClass));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
