function getCookie(name){
	var strcookie=document.cookie;//��ȡcookie���ַ���
	var arrcookie=strcookie.split(";");//�ָ�
	//����ƥ��
	for(var i=0;i<arrcookie.length;i++){
		var arr=arrcookie[i].split("=");
		if(arr[0]==name){
			return arr1[1];
		}
	}
	return "";
}