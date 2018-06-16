package service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

import model.Err;
import model.PageBean;
import model.Table;
import model.User;
import model.UserControl;

public class UserService {
	
	public User queryUser(User user) {
		UserControl userControl=new UserControl();
		return userControl.queryUser(user);
	}
	
	public int queryCount(){
		UserControl userControl=new UserControl();
		try {
			return userControl.queryCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Table<User> queryUsers(PageBean<User> pageBean){
		Table<User> table=new Table<User>();
		UserControl userControl=new UserControl();
		try {
			ArrayList<User> list=userControl.queryAll(pageBean);
			table.setCount(list.size());
			table.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public Err updateUser(User user){
		UserControl userControl=new UserControl();
		Err err=new Err();
		if(user.getNameString()!=null){
			if(user.getPassword()!=null){
				if (user.getNameString().length()<11) {
					if (user.getPassword().length()>5&&user.getPassword().length()<16) {
						if(queryUser(user)!=null){
							if(queryUser(user).getId()==user.getId()){//ͬ�����Խ��и���
								try {
									userControl.updateUser(user);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								err.setErrno(0);
								err.setErrmsg("���ĳɹ�");
							}
							else{//���û����Ѵ���
								err.setErrno(1);
								err.setErrmsg("���û����Ѵ���");
							}
						}
						else{//������ͬ���û������Խ��и���
							try {
								userControl.updateUser(user);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							err.setErrno(0);
							err.setErrmsg("���ĳɹ�");
						}
					} else {//���볤�Ȳ�����6~15λ
						err.setErrno(2);
						err.setErrmsg("���볤��Ϊ6~15λ");
					}
				} else {//�û����ĳ���Ӧ������10λ
					err.setErrno(3);
					err.setErrmsg("�û����ĳ���Ӧ������10λ");
				}
				
			}
			else{//���벻��Ϊ��
				err.setErrno(4);
				err.setErrmsg("���벻��Ϊ��");
			}
		}
		else{//�û�������Ϊ��
			err.setErrno(5);
			err.setErrmsg("�û�������Ϊ��");
		}
		return err;
	}
	
	public Err deleteUser(User user){
		UserControl userControl=new UserControl();
		Err err=new Err();
		try {
			err.setErrno(0);
			err.setErrmsg("ɾ���ɹ�");
			userControl.delUser(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err.setErrno(1);
			err.setErrmsg("�쳣����");
			e.printStackTrace();
		}
		return err;
	}
	
	public Err addUsers(User user) {
		UserControl userControl=new UserControl();
		Err err=new Err();
		if(user.getNameString()!=null){
			if (user.getPassword()!=null) {
				if (user.getNameString().length()<11) {
					if (user.getPassword().length()>5&&user.getPassword().length()<16) {
						if(queryUser(user)==null){
							try {
								err.setErrno(0);
								err.setErrmsg("�û����ӳɹ�");
								userControl.addUser(user);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								err.setErrno(7);
								err.setErrmsg("�����쳣");
								e.printStackTrace();
							}
						}
						else {//�û����Ѵ���
							err.setErrno(1);
							err.setErrmsg("�û����Ѵ���");
						}
					} else {//���볤����6~15λ֮��
						err.setErrno(2);
						err.setErrmsg("���볤����6~15λ֮��");
					}
				} else {//�û������Ȳ�����10λ
					err.setErrno(3);
					err.setErrmsg("�û������Ȳ�����10λ");
				}
			}
			else {//����Ϊ��
				err.setErrno(4);
				err.setErrmsg("����Ϊ��");
			}
		}
		else {//�û���Ϊ��
			err.setErrno(5);
			err.setErrmsg("�û���Ϊ��");
		}
		return err;
	}
	
	public void updateUserLoginTime (User user) {
		UserControl userControl=new UserControl();
		try {
			userControl.updateUserLoginTime(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		UserService userService = new UserService();
		User user=new User();
		user.setNameString("������");
		user.setId(17);
	}
}
