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
				if(renew_password!=null){//���е��������Ϊ��
					if(new_password.equals(renew_password)){//��������һ��
						if (new_password.length()<6||new_password.length()>15) {
							error.setErrno(1);
							error.setErrmsg("���볤��6~15λ");
						}
						else{
							manager.setManagerName(managerName);
							manager.setManagerPassword(new_password);
							if(manager.getManagerName()!=null){
								if(managerControl.queryManager(manager).getManagerPassword().equals(security.MD5.MD5(old_password))){
									managerControl.updateManager(manager);
									error.setErrno(0);
									error.setErrmsg("������ĳɹ�");
								}
								else {
									error.setErrno(7);
									error.setErrmsg("���������");
								}
							}
							else{//�ỰʧЧ�������µ�½
								error.setErrno(6);
								error.setErrmsg("�ỰʧЧ�������µ�½");
							}
							
							
						}
					}
					else{//�������벻һ��
						error.setErrno(2);
						error.setErrmsg("�������벻һ��");
					}
				}
				else{//�ٴ�ȷ������Ϊ��
					error.setErrno(3);
					error.setErrmsg("�ٴ�ȷ������Ϊ��");
				}
			}else{//������Ϊ��
				error.setErrno(4);
				error.setErrmsg("������Ϊ��");
			}
		}else{//������Ϊ��
			error.setErrno(5);
			error.setErrmsg("������Ϊ��");
		}
		return error;
	}
}
