package model;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBHelper;

public class GoodsControl {
	public ArrayList<Goods> selectAllGoods(Integer status){
		ArrayList goodsArrayList=new ArrayList<Goods>();
		String sqlString="SELECT * FROM tb_goods WHERE status=?;";
		try {
			Connection conn=DBHelper.getConnection();
			PreparedStatement preStatement=conn.prepareStatement(sqlString);
			preStatement.setInt(1, status);
			ResultSet rs=preStatement.executeQuery();
			while(rs.next()){
				Goods goods=new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setClassId(rs.getInt("classId"));
				goods.setSellerId(rs.getInt("sellerId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getDouble("price"));
				goods.setStatus(rs.getInt("status"));
				goods.setPicture(rs.getString("picture"));
				goods.setDescription(rs.getString("description"));
				goods.setSellerContact(rs.getString("sellerContact"));
				goods.setCreateDate(rs.getDate("createDate"));
				goods.setReserveDate(rs.getDate("reserveDate"));
				goods.setBuyDate(rs.getDate("buyDate"));
				goods.setCancelDate(rs.getDate("cancelDate"));
				goods.setBuyerContact(rs.getString("buyerContact"));
				goods.setBuyerId(rs.getInt("buyerId"));
				goodsArrayList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generateSd catch block
			e.printStackTrace();
		}
		return goodsArrayList;
	}
	public Goods selectGoodsById(Integer goodsId){
		Goods goods=new Goods();
		String sqlString="SELECT * FROM tb_goods WHERE goodsId=?;";
		try {
			Connection conn=DBHelper.getConnection();
			PreparedStatement preStatement=conn.prepareStatement(sqlString);
			preStatement.setInt(1, goodsId);
			ResultSet rs=preStatement.executeQuery();
			if(rs.next()){
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setClassId(rs.getInt("classId"));
				goods.setSellerId(rs.getInt("sellerId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getDouble("price"));
				goods.setStatus(rs.getInt("status"));
				goods.setPicture(rs.getString("picture"));
				goods.setDescription(rs.getString("description"));
				goods.setSellerContact(rs.getString("sellerContact"));
				goods.setCreateDate(rs.getDate("createDate"));
				goods.setReserveDate(rs.getDate("reserveDate"));
				goods.setBuyDate(rs.getDate("buyDate"));
				goods.setCancelDate(rs.getDate("cancelDate"));
				goods.setBuyerContact(rs.getString("buyerContact"));
				goods.setBuyerId(rs.getInt("buyerId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generateSd catch block
			e.printStackTrace();
		}
		return goods;
	}	
	public void insertGoods(Goods goods){
		String sqlString="INSERT INTO tb_goods (classId,sellerId,goodsName," +
				"price,status,picture,description,sellerContact) VALUES (?,?,?,?,?,?,?,?);";
		try {
			Connection connection=DBHelper.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, goods.getClassId());
			preparedStatement.setInt(2, goods.getSellerId());
			preparedStatement.setString(3, goods.getGoodsName());
			preparedStatement.setDouble(4, goods.getPrice());
			preparedStatement.setInt(5, goods.getStatus());
			preparedStatement.setString(6, goods.getPicture());
			preparedStatement.setString(7, goods.getDescription());
			preparedStatement.setString(8, goods.getSellerContact());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodsControl goodsControl=new GoodsControl();
		Goods goods=new Goods(2,1,"ÊÖ»ú",1999.99,1," ","Apple X","18730272603","57436746@qq.com",1);
		goodsControl.insertGoods(goods);
		ArrayList<Goods> goodsArrayList=goodsControl.selectAllGoods(1);
		for(Goods goods1:goodsArrayList){
			System.out.println(goods1.getGoodsId());
			System.out.println(goods1.getClassId());
			System.out.println(goods1.getSellerId());
			System.out.println(goods1.getGoodsName());
			System.out.println(goods1.getPrice());
			System.out.println(goods1.getStatus());
			System.out.println(goods1.getDescription());
			System.out.println(goods1.getSellerContact());
			System.out.println(goods1.getCreateDate());
			System.out.println(goods1.getReserveDate());
			System.out.println(goods1.getBuyDate());
			System.out.println(goods1.getCancelDate());
			System.out.println(goods1.getBuyerContact());
			System.out.println(goods1.getBuyerId());
		}
	}

}
