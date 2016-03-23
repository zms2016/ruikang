<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 
request.setCharacterEncoding("UTF-8");
 
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
	<title>内容信息</title>
	
	<base href="<%=basePath%>">
	
	 
	              
	           <!--    bootstrap -->
	    <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

        <script src="js/jquery.min.js"></script>
        <script src="components/bootstrap/js/bootstrap.min.js"></script>
 
 
</head>
<body  >
 
 <div class="row"  style="margin:10px;margin-bottom:40px">
		 

			<div class="panel panel-default style="margin:10px; padding-bottom:20px">
				<div class="panel-heading"><strong style="font-size:2em">${ content.filetitle} </strong></div>
				<div class="panel-body">
				       
				             
				             <c:if test="${content.imagefilename!=null }">
				               <h4>文章图片：</h4>
				                    	<img src="/newsPic/${content.imagefilename }" width="120"   height="120"/>
				                    	<hr>
				             </c:if>
				    
				           	
				         
				         <h4>原文：</h4>
					     ${content.content }
					     
					     
				</div>
			</div>

	 
	</div>
  
</body>
</html>


 <script>
 
      
 </script>
 
 