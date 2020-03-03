<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- HTML5文档-->
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
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>修改文章页面</h3></center>
    <form action="${pageContext.request.contextPath}/updateBlogServlet" method="post">
        <input type="hidden" name="id" value="${blog.id}"/>
        <div class="form-group">
            <label for="title">标题：</label>
            <input type="text" class="form-control" id="title" name="title" value="${blog.title}" placeholder="请输入标题">
        </div>

        <div class="form-group">
            <label for="type">分类：</label>
            <select name="type" class="form-control" id="type">
                <option value="情感" <c:if test="${blog.type == '情感'}">selected</c:if>>情感</option>
                <option value="技术" <c:if test="${blog.type == '技术'}">selected</c:if>>技术</option>
                <option value="资讯" <c:if test="${blog.type == '资讯'}">selected</c:if>>资讯</option>
            </select>
        </div>
        <div class="form-group">
            <label for="summary">摘要：</label>
            <input type="text" class="form-control" id="summary" name="summary" value="${blog.summary}" placeholder="请输入摘要">
        </div>
        <div class="form-group">
            <label for="content">内容：</label>
            <input type="text" class="form-control" id="content" name="content" value="${blog.content}" placeholder="请输入内容"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
</html>