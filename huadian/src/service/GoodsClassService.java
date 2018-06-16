package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ClassControl;
import model.Err;
import model.Goods;
import model.GoodsClass;
import model.PageBean;
import model.Table;

public class GoodsClassService {
	public GoodsClass queryGoodsClass(Goods goods){
		ClassControl classControl=new ClassControl();
		GoodsClass goodsClass=classControl.queryClassName(goods);
		return goodsClass ;
	}
	
	public int queryCount(){
		ClassControl classControl=new ClassControl();
		return classControl.queryCount();
	}
	
	public Table<GoodsClass> queryGoodsClass(PageBean<GoodsClass> pageBean){
		Table<GoodsClass> table=new Table<GoodsClass>();
		ClassControl classControl=new ClassControl();
		ArrayList<GoodsClass> list=classControl.queryAll(pageBean);
		table.setCount(list.size());
		table.setList(list);
		return table;
	}
	
	public Err addGoodsClass(GoodsClass goodsClass) {
		ClassControl classControl=new ClassControl();
		Err err=new Err();
		if(goodsClass.getClassName()!=null){
			if (goodsClass.getClassName().length()<11) {
				try {
					if(classControl.queryGoodsClass(goodsClass)==null){//增加种类成功
						classControl.addGoodsClass(goodsClass);
						err.setErrno(0);
						err.setErrmsg("增加种类成功");
					}
					else {//该种类已存在
						err.setErrno(1);
						err.setErrmsg("该种类已存在");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {//商品种类的名称长度不超过10位
				err.setErrno(2);
				err.setErrmsg("商品种类的名称长度不超过10位");
			}
		}
		else {//商品种类的名称不可为空
			err.setErrno(3);
			err.setErrmsg("商品种类的名称不可为空");
		}
		return err;
	}
	
	public Err deleteGoodsClass(GoodsClass goodsClass){
		ClassControl classControl=new ClassControl();
		Err err=new Err();
		try {
			err.setErrno(0);
			err.setErrmsg("删除成功");
			classControl.delGoodsClass(goodsClass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err.setErrno(1);
			err.setErrmsg("异常错误");
			e.printStackTrace();
		}
		return err;
	}
	
	public Err updateGoodsClass(GoodsClass goodsClass) {
		ClassControl classControl=new ClassControl();
		Err err=new Err();
		if(goodsClass.getClassName()!=null){
			if (goodsClass.getClassName().length()<11) {
				try {
					if(classControl.queryGoodsClass(goodsClass)==null){//无同名商品名称，商品种类可以进行更新
						err.setErrno(0);
						err.setErrmsg("商品种类更新成功");
						classControl.updateGoodsClass(goodsClass);
					}
					else {//有同名商品存在
						if(classControl.queryGoodsClass(goodsClass).getClassId()==goodsClass.getClassId()){//同名商品是其本身
							err.setErrno(0);
							err.setErrmsg("商品种类更新成功");
							classControl.updateGoodsClass(goodsClass);
						}
						else {//同名商品名称不是其本身
							err.setErrno(1);
							err.setErrmsg("商品种类名称已存在");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {//商品种类名不超多10位
				err.setErrno(2);
				err.setErrmsg("商品种类名称不超过10位");
			}
		}
		else {//商品种类名为空
			err.setErrno(3);
			err.setErrmsg("商品种类不可为空");
		}
		return err;
	}
	
}
