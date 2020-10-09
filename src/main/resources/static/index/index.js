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
    templateObj.constant.aop = $("#aop");
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

    /**
     * 测试aop的功能
     */
    templateObj.operator.onAopClick = function(){
        $.ajax({
            type:'post',
            url:"/aop/addUser.do",
            dataType:'json',  //返回数据类型
            data :{userName:"AAA"},
            success:function (resultData) {
               console.log("执行成功")
            }
        })
    }

    //初始化
    templateObj.init = function(){

    }

    //绑定点击事件
    templateObj.bind = function () {
        templateObj.constant.logout.bind("click",templateObj.operator.onLogoutClick);
        templateObj.constant.aop.bind("click",templateObj.operator.onAopClick);
    }

    return templateObj;
}