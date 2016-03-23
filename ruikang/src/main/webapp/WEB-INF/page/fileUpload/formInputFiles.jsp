<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
    
    <base href="<%=basePath%>">
    
       <title>表单 一个Input  多file提交</title>

    <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

    <script src="js/jquery.min.js"></script>

    <script src="components/bootstrap/js/bootstrap.min.js"></script>

    

</head>


<body>


 

<form name="filesForm"  id="filesForm" action="formFilesAction.html" enctype="multipart/form-data" method="post" style="margin-top:50px">

      <input type="hidden"  id="msg" name="msg"  value="我是谁">
 
         <input type="file" name="files"    multiple="multiple">
         
         <input type="submit" value="提交">
 
</form>


 
 

</body>
</html>