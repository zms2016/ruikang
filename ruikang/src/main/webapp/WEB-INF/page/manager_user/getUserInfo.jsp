<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>用户信息</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>


</head>

<body>

        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">用户管理</a></li>
  <li class="active">用户信息详情</li>
</ol>


	<div class="row" style="margin:5px">
		<div class="col-md-6">

			<div class="panel panel-default">
				<div class="panel-heading">用户信息</div>
				<div class="panel-body">
				    登录名: ${user.username} <br> ------------------------------<br>
					姓名: ${user.realname} <br> ------------------------------<br>
					部门: ${user.address}<br> ------------------------------<br>

					出生日期:
					<fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日" />
					<br>

				


					<div style="margin-left:20px;margin-bottom:40px"  id="roleslist">
						<c:forEach var="role" items="${roleList }" varStatus="index">

							<label class="checkbox-inline">
							        <input type="checkbox" 	id="${role.id }" value="${role.id }" name="roleids"   readonly > ${role.name }
							</label>

							<c:if test="${index.count%3==0 }">
								</br>
							</c:if>

						</c:forEach>

					</div>


	                <%-- 取tomcat 根目录   --%>
					<img src="/userFacePic/${user.pic }" width="120"   height="120"/>
					
					
				</div>
			</div>

		</div>
	</div>

<script>

	var  modules =new Array();
    
        function init()
        {
   
       	       modules="${roleIds}".split(",");
                 
       	    $("#roleslist :checkbox").each(function() {
       	    	
       	      if ($.inArray($(this).val(),modules)>-1) {
       	    	  
       	        $(this).attr("checked","checked");
       	      }
       	    });
        	
        }
        
        init();

</script>

</body>
</html>
