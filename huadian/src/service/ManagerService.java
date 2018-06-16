package service;

import model.Manager;
import model.ManagerControl;
import model.Err;

public class ManagerService {
	
	public Err managerUpdate(String old_password,String new_password,String renew_password,String managerName) {
		Err error=new Err();
		ManagerControl managerControl=new ManagerControl();
		Manager manager=new Manager();
		if(old_password!=null){
			if(new_password!=null){
				if(renew_password!=null){//所有的密码均不为空
					if(new_password.equals(renew_password)){//两次密码一致
						if (new_password.length()<6||new_password.length()>15) {
							error.setErrno(1);
							error.setErrmsg("密码长度6~15位");
						}
						else{
							manager.setManagerName(managerName);
							manager.setManagerPassword(new_password);
							if(manager.getManagerName()!=null){
								if(managerControl.queryManager(manager).getManagerPassword().equals(security.MD5.MD5(old_password))){
									managerControl.updateManager(manager);
									error.setErrno(0);
									error.setErrmsg("密码更改成功");
								}
								else {
									error.setErrno(7);
									error.setErrmsg("旧密码错误");
								}
							}
							else{//会话失效，请重新登陆
								error.setErrno(6);
								error.setErrmsg("会话失效，请重新登陆");
							}
							
							
						}
					}
					else{//两次密码不一致
						error.setErrno(2);
						error.setErrmsg("两次密码不一致");
					}
				}
				else{//再次确认密码为空
					error.setErrno(3);
					error.setErrmsg("再次确认密码为空");
				}
			}else{//新密码为空
				error.setErrno(4);
				error.setErrmsg("新密码为空");
			}
		}else{//旧密码为空
			error.setErrno(5);
			error.setErrmsg("旧密码为空");
		}
		return error;
	}
}
