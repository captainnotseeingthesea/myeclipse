
package model;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.jmx.snmp.Timestamp;

import service.GoodsService;

import db.DBHelper;

public class GoodsWantControl {
	public Goods setGoods(ResultSet rs) {
		Goods goods=new Goods();
		
		try {
			goods.setGoodsId(rs.getInt("goodsId"));
			goods.setClassId(rs.getInt("classId"));
			goods.setSellerId(rs.getInt("sellerId"));
			goods.setGoodsName(rs.getString("goodsName"));
			goods.setPrice(rs.getDouble("price"));
			goods.setStatus(rs.getInt("status"));
			goods.setPicture(rs.getString("picture"));
			goods.setDescription(rs.getString("description"));
			goods.setSellerContact(rs.getString("sellerContact"));
			goods.setCreateDate(rs.getTimestamp("createDate"));
			goods.setReserveDate(rs.getTimestamp("reserveDate"));
			goods.setBuyDate(rs.getTimestamp("buyDate"));
			goods.setCancelDate(rs.getTimestamp("cancelDate"));
			goods.setBuyerContact(rs.getString("buyerContact"));
			goods.setBuyerId(rs.getInt("buyerId"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	public ArrayList<Goods> selectAllGoods(Integer status){
		ArrayList goodsArrayList=new ArrayList<Goods>();
		String sqlString="SELECT * FROM tb_goods_want WHERE status=?;";
		try {
			Connection conn=DBHelper.getConnection();
			PreparedStatement preStatement=conn.prepareStatement(sqlString);
			preStatement.setInt(1, status);
			ResultSet rs=preStatement.executeQuery();
			while(rs.next()){
				Goods goods=new Goods();
				goods=setGoods(rs);
				goodsArrayList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generateSd catch block
			e.printStackTrace();
		}
		return goodsArrayList;
	}
	
	public Integer selectGoodsNumByCondition(Integer status,String condition,Goods goods) {
		String sql="select count(*) from tb_goods_want where status=?";
		int num=0;
		try {
			Connection connection=DBHelper.getConnection();
			PreparedStatement ptmt=connection.prepareStatement("");
			if(condition!=null&&condition.equals("all")){
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
			}
			else if (condition!=null&&condition.equals("class")) {
				sql+=" and classId = ?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, goods.getClassId());
			}
			else if (condition!=null&&condition.equals("goodsName")) {
				sql+=" and goodsName like ?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setString(2,"%"+goods.getGoodsName()+"%");
			}
			else if (condition!=null&&condition.equals("sellerId")) {
				sql+=" and sellerId = ?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, goods.getSellerId());
			}
			else if (condition!=null&&condition.equals("buyerId")) {
				sql+=" and buyerId = ?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, goods.getBuyerId());
			}
			ResultSet rs=ptmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public PageBean<Goods> selectGoodsByContion(Integer status,PageBean<Goods> pageBean,String condition,Goods goods){
		ArrayList<Goods> arrayList=new ArrayList<Goods>();
		String sql="select * from tb_goods_want where status=?";
		Connection connection;
		try {
			connection = DBHelper.getConnection();
			PreparedStatement ptmt=connection.prepareStatement("");
			if(condition!=null&&condition.equals("all")){
				sql+=" limit ?,?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, pageBean.getStartIndex());
				ptmt.setInt(3, pageBean.getPageSize());
			}
			else if (condition!=null&&condition.equals("class")) {
				sql+=" and classId = ? limit ?,?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, goods.getClassId());
				ptmt.setInt(3, pageBean.getStartIndex());
				ptmt.setInt(4, pageBean.getPageSize());
			}
			else if (condition!=null&&condition.equals("goodsName")) {
				sql+=" and goodsName like ? limit ?,?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setString(2, "%"+goods.getGoodsName()+"%");
				ptmt.setInt(3, pageBean.getStartIndex());
				ptmt.setInt(4, pageBean.getPageSize());
			}
			else if (condition!=null&&condition.equals("sellerId")) {
				sql+=" and sellerId = ? limit ?,?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, goods.getSellerId());
				ptmt.setInt(3, pageBean.getStartIndex());
				ptmt.setInt(4, pageBean.getPageSize());
			}
			else if (condition!=null&&condition.equals("buyerId")) {
				sql+=" and buyerId = ? limit ?,?";
				ptmt=connection.prepareStatement(sql);
				ptmt.setInt(1, status);
				ptmt.setInt(2, goods.getBuyerId());
				ptmt.setInt(3, pageBean.getStartIndex());
				ptmt.setInt(4, pageBean.getPageSize());
			}
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()){
				Goods newGoods=new Goods();
				newGoods=setGoods(rs);
				arrayList.add(newGoods);
			}
			pageBean.setList(arrayList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBean;
		
	}
	
	public Goods selectGoodsById(Integer goodsId){
		Goods goods=new Goods();
		String sqlString="SELECT * FROM tb_goods_want WHERE goodsId=?;";
		try {
			Connection conn=DBHelper.getConnection();
			PreparedStatement preStatement=conn.prepareStatement(sqlString);
			preStatement.setInt(1, goodsId);
			ResultSet rs=preStatement.executeQuery();
			if(rs.next()){
				goods=setGoods(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generateSd catch block
			e.printStackTrace();
		}
		return goods;
	}	
	public void insertGoods(Goods goods){
		String sqlString="INSERT INTO tb_goods_want (classId,sellerId,goodsName," +
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
	
	public void updateGoods(Goods goods) {
		String sql="update tb_goods_want set classId=?,goodsName=?,price=?,status=?,picture=?,description=?,sellerContact=?,reserveDate=?,buyDate=?,cancelDate=?,buyerContact=?,buyerId=? where goodsId=?";
		try {
			Connection connection=DBHelper.getConnection();
			PreparedStatement ptmt=connection.prepareStatement(sql);
			ptmt.setInt(1, goods.getClassId());
			ptmt.setString(2, goods.getGoodsName());
			ptmt.setDouble(3, goods.getPrice());
			ptmt.setInt(4, goods.getStatus());
			ptmt.setString(5, goods.getPicture());
			ptmt.setString(6, goods.getDescription());
			ptmt.setString(7, goods.getSellerContact());
			ptmt.setTimestamp(8, goods.getReserveDate());
			ptmt.setTimestamp(9, goods.getBuyDate());
			ptmt.setTimestamp(10, goods.getCancelDate());
			ptmt.setString(11, goods.getBuyerContact());
			ptmt.setInt(12, goods.getBuyerId());
			ptmt.setInt(13, goods.getGoodsId());
			ptmt.execute();
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
		//goodsControl.insertGoods(goods);
		goods.setGoodsId(75);
		goods.setReserveDate(new java.sql.Timestamp(System.currentTimeMillis()));
		/*ArrayList<Goods> goodsArrayList=goodsControl.selectAllGoods(1);*/
		GoodsService goodsService=new GoodsService();
		goodsService.updateGoods(goods);
		/*PageBean<Goods> pageBean=new PageBean<Goods>();
		pageBean.setPageNum(1);
		pageBean.setPageSize(4);
		ArrayList<Goods> goodsArrayList=goodsService.queryGoodsByCondition(pageBean, goods, "class").getList();
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
		}*/
	}

}
