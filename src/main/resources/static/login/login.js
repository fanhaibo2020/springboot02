/**
 *  登录
 */
$(function () {
    var loginTemplate = new createLoginTemplate();
    loginTemplate.bind();
    loginTemplate.init();
});

function createLoginTemplate() {
   var templateObj = {};
   templateObj.constant = {};//常量
   templateObj.variable = {};//变量
   templateObj.url = {};
   templateObj.operator = {};
   templateObj.inner = {};

   templateObj.constant.loginBtn = $("#loginBtn");
   templateObj.constant.loginUsername = $("#loginUsername");
   templateObj.constant.loginPassword = $("#loginPassword");
   templateObj.url.loginUrl = "/login.do";


    // 登录按钮点击事件
    templateObj.operator.onLoginBtnClick = function(){
        var loginUsername = $(templateObj.constant.loginUsername).val();
        var loginPassword = $(templateObj.constant.loginPassword).val();
        if ($.trim(loginUsername) == '' || $.trim(loginUsername).length<=0){
            layer.msg("用户名不能为空");
            $(templateObj.constant.loginUsername).focus();
            return false;
        }
        if ($.trim(loginPassword) == '' || $.trim(loginPassword).length<=0){
            layer.msg("密码不能为空");
            $(templateObj.constant.loginPassword).focus();
            return false;
        }
        var requestData = {}
        requestData.userName = loginUsername;
        requestData.password = loginPassword;
        console.log("requestData120=",JSON.stringify(requestData));
        $.ajax({
            type:'POST',
            url:templateObj.url.loginUrl,
            // contentType: 'application/json; charset=utf-8',//设置发送给服务器的格式(不建议使用)
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            }, //设置发送给服务器的格式,这个应该是默认的,之前不显示设置也是可以运行的,为什么后来就不行了？
            dataType:'json',  //返回数据类型(收到服务器数据格式)
            data: {userName:loginUsername,password:loginPassword},  //发送到后端的数据  JSON.stringify(params)，传过去的是一个对象
            success:function (resultData) {
                if(resultData.flag ==true){
                    window.location.href = "/index.html";//请求页面的url
                }else{
                    $("#errorDiv").show();//登录失败提示错误信息
                }
            }
        })
    }

    //初始化
    templateObj.init = function(){

    }

    templateObj.operator.onEnterBtnClick = function(){
        if(event.keyCode==13){
            templateObj.operator.onLoginBtnClick(); //执行函数
        }
    }

   //绑定点击事件
   templateObj.bind = function () {
       templateObj.constant.loginBtn.bind("click",templateObj.operator.onLoginBtnClick); //登录按钮点击事件
       $(document).bind("keyup",templateObj.operator.onEnterBtnClick)//回车键键绑定点击事件

   }
   return templateObj;
}