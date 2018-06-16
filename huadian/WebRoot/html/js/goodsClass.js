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
        , url: site_url+"/servlet/ManageGoodsClass"  //���ݽӿ�
        , method: 'post'
        , cellMinWidth: 80
        , page: true //������ҳ
        , limit:10
        , width:1130
        , cols: [[ //��ͷ
                   {field: 'kid', title: 'ID', width:350, sort: true, fixed: 'left'}
                   ,{field: 'className', title: '��Ʒ��������', width:350}
                   ,{fixed: 'right', width: 425, align:'center', toolbar: '#operation-bar'}
        ]]
    });
    
    $('#addGoodsClass').on('click',function(){
    	layer_add_goodsClass=layer.open({
    		type:1,
    		title:['������Ʒ������Ϣ','font-size:13px;margin-top:10px'],
    		content:$("#add_goods_class").html()
    	});
    });
    
    form.on('submit(add_goods_class)', function(data){//������Ʒ����
        return base_ajax(site_url+"/servlet/AddGoodsClass",data.field,function () {
            table.reload('goodsClass', {
                url: site_url+"/servlet/ManageGoodsClass"
            });
            layer.close(layer_add_goodsClass);
        });
    });
    
    form.on('submit(update_goodsClass)',function(data){//�޸���Ʒ����������
    	return base_ajax(site_url+"/servlet/UpdateGoodsClass",data.field,function () {
            table.reload('goodsClass', {
                url: site_url+"/servlet/ManageGoodsClass"
            });
            layer.close(layer_update_goodsClass);
        });
    });
    
    table.on('tool(demo)', function(obj){ //ע��tool�ǹ������¼�����test��tableԭʼ���������� lay-filter="��Ӧ��ֵ"
        var data = obj.data //��õ�ǰ������
        ,layEvent = obj.event //��� lay-event ��Ӧ��ֵ
        if(layEvent === 'del'){
          layer.confirm('���ɾ����', function(index){
        	return base_ajax(site_url+"/servlet/DeleteGoodsClass",obj.data,function(){
	        	obj.del(); //ɾ����Ӧ�У�tr����DOM�ṹ
	            layer.close(index);
        	});
          });
        } else if(layEvent === 'edit'){
        	layer_update_goodsClass=layer.open({
            	type:1,
    			title:['��Ʒ������Ϣ-�޸�','font-size:13px;margin-top:10px'],
    			content:$('#updateGoodsClass').html(),
            });
        	$("#update_goodsClass :text").val(obj.data.className);
        	$("#update_goodsClass :hidden").val(obj.data.classId);
        }
      });
    
});
