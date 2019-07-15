function abc(){ 
    return "http://47.107.180.49"
}

function MyAjax(type,url,data,token){
        if(token==null||token==""){
            token=null;
        }else{
            token= {
                'sessionId': token
              }
        }
    var responesData="";
    $.ajax({
        type:type,
        url: abc() +url,
        async: false,
        data:data,
        headers: token,
        success: function (data) {
            if (data.code === 0) {
                    responesData=data;
                    return;
            } 
            if (data.code ===1) {
                layer.msg(data.msg)
                return;
            } 
           
           if (data.code ===-1) {
            layer.msg(data.msg)
            return;
             } 

             
           
           if (data.code ===-1) {
            layer.msg("无权限进行此操作")
            return;
             } 
            
           if (data.code ===100) {
            layer.msg("登录失效，请重新登录")
            window.location.href = "../html/login.html"
            return;
             }  
        }
    });
    return responesData;
}
function getUser(){
    return layui.sessionData('user').userInfo;
}
function getToken(){
    return layui.sessionData('user').userInfo.sessionId;
}
/**
 * 适用于 获取地址栏参数
 * @param paraName  要获取的参数名  例如 要获取 www.baidu.com?id=1  则 传id参数
 * @returns {string}
 */
function getParam(paraName){
    var url = document.location.toString();
    var arrObj = url.split("?");

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");

            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    }
    else {
        return "";
    }

    /**
     * 全屏模式
     */
    function goBig(){
        var el = document.documentElement;
        var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen;
        if (rfs) {
            rfs.call(el);
        } else if (typeof window.ActiveXObject !== "undefined") {
            //for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript != null) {
                wscript.SendKeys("{F11}");
            }
        }
    }
}