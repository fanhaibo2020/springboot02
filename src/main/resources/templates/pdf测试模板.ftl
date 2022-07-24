<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            font-family: SimSun;
        }
        section{
            display:block;
            margin: 20px 10px;
        }
        .title{
            text-align: center;
        }
        .preface p{
            line-height: 30px;
        }
        .preface p.content{
            text-indent: 2em;
        }
        section > table{
            table-layout: fixed;
            width: 100%;
            margin: 20px 0px;
            text-align:center;
            word-wrap:break-word;
        }
        section table td{
            padding:5px 0px;
        }
        tr { page-break-inside:avoid; page-break-after:auto;}

        /*页眉页脚样式start*/
        @page {
            @top-center {content: element(header)}      /* Header */
            @bottom-center {content: element(footer)}   /* Enpied */
        }
        #header {position: running(header);}
        #footer {position: running(footer);}
        #pagenumber:before {content: counter(page);}
        #pagecount:before {content: counter(pages);}  /*向id为pagecount前面插入内容,数据为xx*/

        /*@page规则定义styleSheet中页面框的尺寸，方向和页边距*/
        /*content作用: 插入内容*/
        /*counter作用: 计数*/
        /*element作用: 选择器*/
        /*页眉页脚样式end*/

        /*解决表格跨页的样式控制start*/
        table{
            page-break-inside:auto;
            -fs-table-paginate:paginate;
            border-spacing:0;table-layout:fixed;
            word- break:break- strict;
            cellspacing:0;cellpadding:0;
            border: solid 0px #ccc; padding: 2px 2px;
        }
        tr { page-break-inside:avoid; page-break-after:auto;}
        /*解决表格跨页的样式控制end*/
    </style>
</head>
<body>

<#--页眉页脚start-->
<#--<div id="header"> 页眉:GFKD分房名单</div>-->
<div id="footer" style="text-align: center">第 <span id="pagenumber" /> 页/共 <span id="pagecount" /> 页</div>
<#--页眉页脚end-->

    <!-- 标题 start -->
    <section class="title" >
        <h2>住房公示</h2>
    </section>
    <!-- 标题 end -->

    <!-- 前言 start -->
    <section class="preface">
        <p>尊敬的用户：</p>
        <p class="content">以下为本次选房名单公示，如有异议，请于xx月xx日之前向xxx反应</p>
    </section>
    <!-- 前言 end -->

    <!-- 汇总统计信息 start -->
    <section class="count-info">
        <h4>汇总统计信息</h4>
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td>本月笔数</td>
                <td>近三个月数量对比</td>
            </tr>
        </table>
    </section>
    <!-- 汇总统计信息 end -->

    <!-- 明细 start -->
    <section class="detail">
        <h4>明细</h4>
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td width="5%">序号</td>
                <td width="10%">姓名</td>
                <td width="5%">性别</td>
                <td width="20%">身份证号</td>
                <td>住址</td>
            </tr>
            <#list detailList as detail>
                <tr>
                    <td>${detail_index+1}</td>
                    <td>${detail.userName}</td>
                    <td>${detail.gender}</td>
                    <td>${detail.idCardNo}</td>
                    <td>${detail.address}</td>
                </tr>
            </#list>
        </table>
    </section>
    <!-- 明细 end -->
</body>
</html>
