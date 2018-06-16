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
					if(classControl.queryGoodsClass(goodsClass)==null){//��������ɹ�
						classControl.addGoodsClass(goodsClass);
						err.setErrno(0);
						err.setErrmsg("��������ɹ�");
					}
					else {//�������Ѵ���
						err.setErrno(1);
						err.setErrmsg("�������Ѵ���");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {//��Ʒ��������Ƴ��Ȳ�����10λ
				err.setErrno(2);
				err.setErrmsg("��Ʒ��������Ƴ��Ȳ�����10λ");
			}
		}
		else {//��Ʒ��������Ʋ���Ϊ��
			err.setErrno(3);
			err.setErrmsg("��Ʒ��������Ʋ���Ϊ��");
		}
		return err;
	}
	
	public Err deleteGoodsClass(GoodsClass goodsClass){
		ClassControl classControl=new ClassControl();
		Err err=new Err();
		try {
			err.setErrno(0);
			err.setErrmsg("ɾ���ɹ�");
			classControl.delGoodsClass(goodsClass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err.setErrno(1);
			err.setErrmsg("�쳣����");
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
					if(classControl.queryGoodsClass(goodsClass)==null){//��ͬ����Ʒ���ƣ���Ʒ������Խ��и���
						err.setErrno(0);
						err.setErrmsg("��Ʒ������³ɹ�");
						classControl.updateGoodsClass(goodsClass);
					}
					else {//��ͬ����Ʒ����
						if(classControl.queryGoodsClass(goodsClass).getClassId()==goodsClass.getClassId()){//ͬ����Ʒ���䱾��
							err.setErrno(0);
							err.setErrmsg("��Ʒ������³ɹ�");
							classControl.updateGoodsClass(goodsClass);
						}
						else {//ͬ����Ʒ���Ʋ����䱾��
							err.setErrno(1);
							err.setErrmsg("��Ʒ���������Ѵ���");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {//��Ʒ������������10λ
				err.setErrno(2);
				err.setErrmsg("��Ʒ�������Ʋ�����10λ");
			}
		}
		else {//��Ʒ������Ϊ��
			err.setErrno(3);
			err.setErrmsg("��Ʒ���಻��Ϊ��");
		}
		return err;
	}
	
}
