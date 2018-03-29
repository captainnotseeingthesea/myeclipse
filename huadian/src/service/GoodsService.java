package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Goods;
import model.GoodsControl;
import model.User;
import model.UserControl;

public class GoodsService {
	public void addGoods(Goods goods,User user){
		UserControl userControl=new UserControl();
		GoodsControl goodsControl=new GoodsControl();
		Integer userId;
		userId = userControl.queryUserId(user).getId();
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
}
