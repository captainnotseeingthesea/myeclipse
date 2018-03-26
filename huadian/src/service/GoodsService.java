package service;

import java.sql.SQLException;

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
}
