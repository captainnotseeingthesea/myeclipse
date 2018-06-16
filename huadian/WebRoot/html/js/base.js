var site_url = 'http://localhost:8080/huadian';
var base_index = site_url+'/servlet/managerLogin';
function base_ajax(url,data,success_func){
	//var $ = layui.$;
    //var layer = layui.layer;
    var loading = layer.msg('���Ժ�...',{
        icon: 16
        , shade: 0.1
        , time:0
    });
    console.log(data.field);
    $.ajax({
        url: url,
        type: "post",
        dataType : "json",
        data: data,
        success: function(data){
            console.log(data);
            //data = JSON.parse(data);
            if(data.errno === 0 || data.status === 1){
                layer.close(loading);
                layer.msg(data.errmsg,{
                    icon: 1
                    , shade: 0.1
                    , time: 1000
                });
                if(data.remember==1){
                	localStorage.setItem("remember", 1);
				} else {
					localStorage.setItem("remember", 0);
                }
				localStorage.setItem("managerName", $("#managerName").val());
				localStorage.setItem("managerPassword", $("#managerPassword").val());
                if(success_func !== undefined){
                    success_func();
                }
            }else{
                if(data.errmsg != ""){
                    layer.msg(data.errmsg,{
                        icon: 2
                        , shade: 0.1
                        , time: 1000
                    });
                }else{
                    layer.msg("δ֪����",{
                        icon: 2
                        , shade: 0.1
                        , time: 2000
                    });
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(XMLHttpRequest.status + '�ύʧ��', {
                icon: 2
                , shade: 0.1
                , time: 2000
            })
        },
        complete: function (XMLHttpRequest, textStatus) {
            this;
        }
    });
    return false;
}