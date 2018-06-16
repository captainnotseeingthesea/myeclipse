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
        , url: site_url+"/servlet/ManageUsers"  //数据接口
        , method: 'post'
        , cellMinWidth: 80
        , page: true //开启分页
        , limit:10
        , width:1130
        , cols: [[ //表头
                   {field: 'kid', title: 'ID', width:250, sort: true, fixed: 'left'}
                   ,{field: 'nameString', title: '用户名', width:300}
                   ,{field: 'loginTime', title: '上次登陆时间', width:300}
                   ,{fixed: 'right', width: 275, align:'center', toolbar: '#operation-bar'}
        ]]
    });

    $("#addUser").on('click',function(){
    	layer_add_users=layer.open({
    		type:1,
    		title:['新增用户','font-size:13px;margin-top:10px'],
    		content:$("#addUsers").html()
    	});
    });
    
    
    table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
        ,layEvent = obj.event //获得 lay-event 对应的值
        if(layEvent === 'del'){
          layer.confirm('真的删除吗', function(index){
        	return base_ajax(site_url+"/servlet/DeleteUsers",obj.data,function(){
	        	obj.del(); //删除对应行（tr）的DOM结构
	            layer.close(index);
        	});
          });
        } else if(layEvent === 'edit'){
        	layer_update_users=layer.open({
            	type:1,
    			title:['用户信息-修改','font-size:13px;margin-top:10px'],
    			content:$('#updateUsers').html(),
            });
        	$("#update_users :text").val(obj.data.nameString);
        	$("#update_users :password").val(obj.data.password);
        	$("#update_users :hidden").val(obj.data.id);
        }
      });
    
    form.on('submit(add_users)', function(data){//新建用户
        return base_ajax(site_url+"/servlet/AddUsers",data.field,function () {
            table.reload('admin', {
                url: site_url+"/servlet/ManageUsers"
            });
            layer.close(layer_add_users);
        });
    });
    
    form.on('submit(update_users)',function(data){//修改用户
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