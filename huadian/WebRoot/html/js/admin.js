layui.use(['element', 'table', 'layer', 'jquery','form'], function () {
    var element = layui.element,
        form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.$,
        layer_add_users,
        layer_update_users;
    table.render({
        elem: '#admin'
        , id: 'admin'
        , height: 488
        , url: site_url+"/servlet/ManageUsers"  //���ݽӿ�
        , method: 'post'
        , cellMinWidth: 80
        , page: true //������ҳ
        , limit:10
        , width:1130
        , cols: [[ //��ͷ
                   {field: 'kid', title: 'ID', width:250, sort: true, fixed: 'left'}
                   ,{field: 'nameString', title: '�û���', width:300}
                   ,{field: 'loginTime', title: '�ϴε�½ʱ��', width:300}
                   ,{fixed: 'right', width: 275, align:'center', toolbar: '#operation-bar'}
        ]]
    });

    $("#addUser").on('click',function(){
    	layer_add_users=layer.open({
    		type:1,
    		title:['�����û�','font-size:13px;margin-top:10px'],
    		content:$("#addUsers").html()
    	});
    });
    
    
    table.on('tool(demo)', function(obj){ //ע��tool�ǹ������¼�����test��tableԭʼ���������� lay-filter="��Ӧ��ֵ"
        var data = obj.data //��õ�ǰ������
        ,layEvent = obj.event //��� lay-event ��Ӧ��ֵ
        if(layEvent === 'del'){
          layer.confirm('���ɾ����', function(index){
        	return base_ajax(site_url+"/servlet/DeleteUsers",obj.data,function(){
	        	obj.del(); //ɾ����Ӧ�У�tr����DOM�ṹ
	            layer.close(index);
        	});
          });
        } else if(layEvent === 'edit'){
        	layer_update_users=layer.open({
            	type:1,
    			title:['�û���Ϣ-�޸�','font-size:13px;margin-top:10px'],
    			content:$('#updateUsers').html(),
            });
        	$("#update_users :text").val(obj.data.nameString);
        	$("#update_users :password").val(obj.data.password);
        	$("#update_users :hidden").val(obj.data.id);
        }
      });
    
    form.on('submit(add_users)', function(data){//�½��û�
        return base_ajax(site_url+"/servlet/AddUsers",data.field,function () {
            table.reload('admin', {
                url: site_url+"/servlet/ManageUsers"
            });
            layer.close(layer_add_users);
        });
    });
    
    form.on('submit(update_users)',function(data){//�޸��û�
    	console.log(data.field);
    	base_ajax(site_url+"/servlet/UpdateUsers",data.field,function(){
    		table.reload('admin',{
    			url:site_url+"/servlet/ManageUsers"
    		});
    		layer.close(layer_update_users);
    	});
    	return false;
    });
});