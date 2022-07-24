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
    templateObj.constant.excel = $("#excel");
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
        templateObj.constant.excel.bind("click",templateObj.operator.onExcelClick);
    }

    //下载excel
    templateObj.operator.onExcelClick = function(){
      alert(12356789);
      layui.use(['excel'],function(){
          var excel = layui.excel;
          // 2. A列宽 150，B列宽200，默认80
          var colConf = excel.makeColConfig({
              'A': 150,
              'B': 200,
          }, 80);
          console.log("colConf="+JSON.stringify(colConf));
          // 3. 第1行行高40，第二行行高30，默认20
          var rowConf = excel.makeRowConfig({
              1: 40,
              3: 30
          }, 20);
          console.log("rowConf="+JSON.stringify(rowConf));
          var mergeConf = excel.makeMergeConfig(["A2","B2"]);
          console.log("mergeConf="+JSON.stringify(mergeConf));
          excel.exportExcel([[1, 2, 3,]], '表格导出123456789.xlsx', 'xlsx',{
              extend: {
                  // extend 中可以指定某个 sheet 的属性，如果不指定 sheet 则所有 sheet 套用同一套属性
                  sheet1: {
                      '!merges': mergeConf,
                      '!cols': colConf
                      ,
                      '!rows': rowConf
                  }
              }
          });
      });
    }

    return templateObj;
}