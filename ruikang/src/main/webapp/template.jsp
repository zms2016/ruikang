<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>新闻资讯</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
 
 
<!-- <link rel="stylesheet" href="css/aboutUS.css"> -->

<link rel="stylesheet" href="css/web_comm.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

 <style>
 
     
 </style>
</head>

<body   class="web_font">

	<%@ include file="/head.jsp"%>


 <div class="  content-warp"   >

         <div class="content-box">

               <div class="left_menu divc-comm">
                   <h3>关于我们</h3>
                     <ul>
                         <li>
                            <a href="#">1</a> <i class="web_icons"></i>
                         </li>

                         <li class="active">
                             <a href="#">21</a><i class="web_icons" ></i>
                         </li>
                     </ul>
               </div>

              <div class="right_content divc-comm">
                  ddddddddddddddddddddddd

              </div>
         </div>

</div>
	 

		<%@ include file="/foot.jsp"%>
</body>

<script>
	//遍历 li 根据后台传递的 id 设置 class
	$("#listItem li").each(function() {

		if (${newItemId} == $(this).attr("id")) {

			$(this).addClass("active");
		}

	});
</script>


</html>
