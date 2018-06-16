var site_url = 'http://localhost:8080/huadian';
var base_index = site_url+'/servlet/ManagerUpdate';
function base_ajax(url,data,success_func){
	//var $ = layui.$;
    //var layer = layui.layer;
    var loading = layer.msg('«Î…‘∫Û...',{
        icon: 16
        , shade: 0.1
        , time:0
    });
    console.log(data);
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
				  if(success_func !== undefined){
                    success_func();
                }
            }else{
            	if(data.errno===6){
            		window.location.href="/huadian/html/managerLogin.html";
            	}
                if(data.errmsg != ""){
                    layer.msg(data.errmsg,{
                        icon: 2
                        , shade: 0.1
                        , time: 1000
                    });
                }else{
                    layer.msg("Œ¥÷™¥ÌŒÛ",{
                        icon: 2
                        , shade: 0.1
                        , time: 2000
                    });
                }
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(XMLHttpRequest.status + 'Ã·Ωª ß∞‹', {
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