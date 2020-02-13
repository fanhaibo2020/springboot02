/**
 *  首页
 */
$(function () {
    var indexTemplate = new createIndexTemplate();
    indexTemplate.bind();
    indexTemplate.init();
});

function createIndexTemplate() {
    var templateObj = {};
    templateObj.constant = {};//常量
    templateObj.variable = {};//变量
    templateObj.url = {};
    templateObj.operator = {};
    templateObj.inner = {};

    templateObj.constant.logout = $("#logout");
    templateObj.url.logoutUrl = "/logout.do";

    // 退出登录按钮点击事件
    templateObj.operator.onLogoutClick = function(){
        $.ajax({
            type:'post',
            url:templateObj.url.logoutUrl,
            dataType:'json',  //返回数据类型
            success:function (resultData) {
                if(resultData.flag ==true){
                    window.location.href = "/login.html"; //请求页面的url
                }
            }

        })
    }

    //初始化
    templateObj.init = function(){

    }

    //绑定点击事件
    templateObj.bind = function () {
        templateObj.constant.logout.bind("click",templateObj.operator.onLogoutClick);
    }

    return templateObj;
}