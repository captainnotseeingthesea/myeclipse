package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Goods;
import model.GoodsControl;
import model.PageBean;
import model.User;
import model.UserControl;

public class GoodsService {
	public void addGoods(Goods goods,User user){
		UserControl userControl=new UserControl();
		GoodsControl goodsControl=new GoodsControl();
		Integer userId;
		userId = userControl.queryUser(user).getId();
		goods.setSellerId(userId);
		goods.setStatus(1);
		goodsControl.insertGoods(goods);
	}
	public ArrayList<Goods> queryGoods(Integer status) {
		GoodsControl goodsControl=new GoodsControl();
		ArrayList<Goods>goodsArrayList=goodsControl.selectAllGoods(status);
		return goodsArrayList;
	}
	public Goods queryGoodsById(Integer goodsId) {
		GoodsControl goodsControl=new GoodsControl();
		Goods goods=goodsControl.selectGoodsById(goodsId);
		return goods;
	}
	public Integer queryGoodsNumByCondition(Integer status,Goods goods,String condition){
		GoodsControl goodsControl=new GoodsControl();
		int num=goodsControl.selectGoodsNumByCondition(status,condition, goods);
		return num;
	}
	public PageBean<Goods> queryGoodsByCondition(Integer status,PageBean<Goods> pageBean,Goods goods,String condition) {
		int num=queryGoodsNumByCondition(status,goods, condition);
		PageBean<Goods> pageBean2=new PageBean<Goods>(pageBean.getPageNum(),pageBean.getPageSize(),num);
		pageBean.setTotalRecord(num);
		GoodsControl goodsControl=new GoodsControl();
		pageBean2=goodsControl.selectGoodsByContion(status,pageBean2, condition, goods);
		return pageBean2;
	}
	public void updateGoods(Goods goods){
		GoodsControl goodsControl=new GoodsControl();
		goodsControl.updateGoods(goods);
	}
	public static void main(String[] args) {
		GoodsService goodsService=new GoodsService();
		PageBean<Goods> pageBean=new PageBean<Goods>();
		pageBean.setPageNum(1);
		pageBean.setPageSize(6);
		Goods goods=new Goods();
		goods.setSellerId(2);
		goodsService.queryGoodsByCondition(3, pageBean, goods, "sellerId");
	}
}
