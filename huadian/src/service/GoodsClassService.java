package service;

import model.ClassControl;
import model.Goods;
import model.GoodsClass;

public class GoodsClassService {
	public GoodsClass queryGoodsClass(Goods goods){
		ClassControl classControl=new ClassControl();
		GoodsClass goodsClass=classControl.queryClassName(goods);
		return goodsClass ;
	}
}
