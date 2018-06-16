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
							if(queryUser(user).getId()==user.getId()){//同名可以进行更改
								try {
									userControl.updateUser(user);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								err.setErrno(0);
								err.setErrmsg("更改成功");
							}
							else{//此用户名已存在
								err.setErrno(1);
								err.setErrmsg("此用户名已存在");
							}
						}
						else{//不存在同名用户，可以进行更改
							try {
								userControl.updateUser(user);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							err.setErrno(0);
							err.setErrmsg("更改成功");
						}
					} else {//密码长度不符合6~15位
						err.setErrno(2);
						err.setErrmsg("密码长度为6~15位");
					}
				} else {//用户名的长度应不超过10位
					err.setErrno(3);
					err.setErrmsg("用户名的长度应不超过10位");
				}
				
			}
			else{//密码不可为空
				err.setErrno(4);
				err.setErrmsg("密码不可为空");
			}
		}
		else{//用户名不可为空
			err.setErrno(5);
			err.setErrmsg("用户名不可为空");
		}
		return err;
	}
	
	public Err deleteUser(User user){
		UserControl userControl=new UserControl();
		Err err=new Err();
		try {
			err.setErrno(0);
			err.setErrmsg("删除成功");
			userControl.delUser(user.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			err.setErrno(1);
			err.setErrmsg("异常错误");
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
								err.setErrmsg("用户增加成功");
								userControl.addUser(user);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								err.setErrno(7);
								err.setErrmsg("增加异常");
								e.printStackTrace();
							}
						}
						else {//用户名已存在
							err.setErrno(1);
							err.setErrmsg("用户名已存在");
						}
					} else {//密码长度在6~15位之间
						err.setErrno(2);
						err.setErrmsg("密码长度在6~15位之间");
					}
				} else {//用户名长度不超过10位
					err.setErrno(3);
					err.setErrmsg("用户名长度不超过10位");
				}
			}
			else {//密码为空
				err.setErrno(4);
				err.setErrmsg("密码为空");
			}
		}
		else {//用户名为空
			err.setErrno(5);
			err.setErrmsg("用户名为空");
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
		user.setNameString("李宣佚");
		user.setId(17);
	}
}
