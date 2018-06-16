layui.use(['element', 'table', 'layer', 'jquery','form'], function () {
    var element = layui.element,
        form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.$,
        layer_add_goodsClass,
        layer_update_goodsClass;
    table.render({
        elem: '#goodsClass'
        , id: 'goodsClass'
        , height: 488
        , url: site_url+"/servlet/ManageGoodsClass"  //数据接口
        , method: 'post'
        , cellMinWidth: 80
        , page: true //开启分页
        , limit:10
        , width:1130
        , cols: [[ //表头
                   {field: 'kid', title: 'ID', width:350, sort: true, fixed: 'left'}
                   ,{field: 'className', title: '商品种类名称', width:350}
                   ,{fixed: 'right', width: 425, align:'center', toolbar: '#operation-bar'}
        ]]
    });
    
    $('#addGoodsClass').on('click',function(){
    	layer_add_goodsClass=layer.open({
    		type:1,
    		title:['新增物品种类信息','font-size:13px;margin-top:10px'],
    		content:$("#add_goods_class").html()
    	});
    });
    
    form.on('submit(add_goods_class)', function(data){//新增商品种类
        return base_ajax(site_url+"/servlet/AddGoodsClass",data.field,function () {
            table.reload('goodsClass', {
                url: site_url+"/servlet/ManageGoodsClass"
            });
            layer.close(layer_add_goodsClass);
        });
    });
    
    form.on('submit(update_goodsClass)',function(data){//修改商品的种类名称
    	return base_ajax(site_url+"/servlet/UpdateGoodsClass",data.field,function () {
            table.reload('goodsClass', {
                url: site_url+"/servlet/ManageGoodsClass"
            });
            layer.close(layer_update_goodsClass);
        });
    });
    
    table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
        ,layEvent = obj.event //获得 lay-event 对应的值
        if(layEvent === 'del'){
          layer.confirm('真的删除吗', function(index){
        	return base_ajax(site_url+"/servlet/DeleteGoodsClass",obj.data,function(){
	        	obj.del(); //删除对应行（tr）的DOM结构
	            layer.close(index);
        	});
          });
        } else if(layEvent === 'edit'){
        	layer_update_goodsClass=layer.open({
            	type:1,
    			title:['物品种类信息-修改','font-size:13px;margin-top:10px'],
    			content:$('#updateGoodsClass').html(),
            });
        	$("#update_goodsClass :text").val(obj.data.className);
        	$("#update_goodsClass :hidden").val(obj.data.classId);
        }
      });
    
});
