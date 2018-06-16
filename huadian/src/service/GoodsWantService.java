package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Goods;
import model.GoodsWantControl;
import model.PageBean;
import model.User;
import model.UserControl;

public class GoodsWantService {
	public void addGoods(Goods goods,User user){
		UserControl userControl=new UserControl();
		GoodsWantControl goodsControl=new GoodsWantControl();
		Integer userId;
		userId = userControl.queryUser(user).getId();
		goods.setSellerId(userId);
		goods.setStatus(1);
		goodsControl.insertGoods(goods);
	}
	public ArrayList<Goods> queryGoods(Integer status) {
		GoodsWantControl goodsControl=new GoodsWantControl();
		ArrayList<Goods>goodsArrayList=goodsControl.selectAllGoods(status);
		return goodsArrayList;
	}
	public Goods queryGoodsById(Integer goodsId) {
		GoodsWantControl goodsControl=new GoodsWantControl();
		Goods goods=goodsControl.selectGoodsById(goodsId);
		return goods;
	}
	public Integer queryGoodsNumByCondition(Integer status,Goods goods,String condition){
		GoodsWantControl goodsControl=new GoodsWantControl();
		int num=goodsControl.selectGoodsNumByCondition(status,condition, goods);
		return num;
	}
	public PageBean<Goods> queryGoodsByCondition(Integer status,PageBean<Goods> pageBean,Goods goods,String condition) {
		int num=queryGoodsNumByCondition(status,goods, condition);
		PageBean<Goods> pageBean2=new PageBean<Goods>(pageBean.getPageNum(),pageBean.getPageSize(),num);
		pageBean.setTotalRecord(num);
		GoodsWantControl goodsControl=new GoodsWantControl();
		pageBean2=goodsControl.selectGoodsByContion(status,pageBean2, condition, goods);
		return pageBean2;
	}
	public void updateGoods(Goods goods){
		GoodsWantControl goodsControl=new GoodsWantControl();
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
