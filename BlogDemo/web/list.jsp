<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function deleteBlog(id) {
        if (confirm("您确定要删除吗？")) {
            location.href = "${pageContext.request.contextPath}/delBlogServlet?id=" + id;
        }
    }

    window.onload = function(){
        //给删除选中按钮添加单击事件
        document.getElementById("delSelected").onclick = function(){
            if(confirm("您确定要删除选中条目吗？")){

                var flag = false;
                //判断是否有选中条目
                var cbs = document.getElementsByName("bid");
                for (var i = 0; i < cbs.length; i++) {
                    if(cbs[i].checked){
                        //有一个条目选中了
                        flag = true;
                        break;
                    }
                }

                if(flag){//有条目被选中
                    //表单提交
                    document.getElementById("form").submit();
                }

            }

        }
        //1.获取第一个cb
        document.getElementById("firstCb").onclick = function(){
            //2.获取下边列表中所有的cb
            var cbs = document.getElementsByName("bid");
            //3.遍历
            for (var i = 0; i < cbs.length; i++) {
                //4.设置这些cbs[i]的checked状态 = firstCb.checked
                cbs[i].checked = this.checked;

            }

        }


    }

    </script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户文章列表</h3>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/findBlogByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">标题</label>
                <input type="text" name="title" value="${condition.title[0]}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">摘要</label>
                <input type="text" name="summary" value="${condition.summary[0]}" class="form-control" id="exampleInputName3" >
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">内容</label>
                <input type="text" name="content" value="${condition.content[0]}" class="form-control" id="exampleInputEmail2"  >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加文章</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedBlogServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>标题</th>
            <th>分类</th>
            <th>摘要</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${blogs}" var="blog" varStatus="s">
        <tr>
            <td><input type="checkbox" name="bid" value="${blog.id}"></td>
            <td>${s.count}</td>
            <td>${blog.title}</td>
            <td>${blog.type}</td>
            <td>${blog.summary}</td>
            <td>${blog.createTime}</td>
            <td>${blog.updateTime}</td>
            <td>
                <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findBlogServlet?id=${blog.id}">修改</a>&nbsp;
                <a class="btn btn-default btn-sm" href="javascript:deleteBlog(${blog.id});">删除</a>
            </td>
        </tr>
        </c:forEach>

        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加文章</a></td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>
