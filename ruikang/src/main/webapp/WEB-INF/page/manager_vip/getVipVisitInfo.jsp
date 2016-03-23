<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="page" uri="http://zms.20032015.com/pager" %>
 

 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>  

<!DOCTYPE HTML>
<html>
<head>
  <base href="<%=basePath%>"> 
<title>拜访记录详情</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 
 
</head>
 
<body>
 
    <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active">拜访记录详情</li>
</ol>
 
  <div class="container">
  
  
 <div class="panel panel-default">
  <div class="panel-heading">客户名称: ${visitVo.vipName }    &nbsp; &nbsp; <fmt:formatDate value="${visitVo.visitTime }" pattern="yyyy年MM月dd日" /> </div>
  <div class="panel-body">
          ${visitVo.content }
          
            <c:if test="${ not empty visitVo.memo }">
            
             <div class="alert alert-warning" role="alert"  style="margin:20px">${visitVo.memo }</div>
             </c:if>
         
          
  </div>
</div>

</div>

</html>
