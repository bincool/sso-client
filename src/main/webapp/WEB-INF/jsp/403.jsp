<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8" />
    <title>403-禁止访问：访问被拒绝</title>

    <meta name="description" content="403 Error Page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <style type="text/css">

        .txt403{
            text-align: center;
            font-size: 28px;
        }
    </style>

<body>
<div class="txtbox">
    <h1 class="txt403">403&nbsp;&nbsp;&nbsp;Forbidden</h1>
</div>
</body>
</html>