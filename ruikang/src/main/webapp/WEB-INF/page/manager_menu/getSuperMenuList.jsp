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
<title>一级菜单列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">菜单管理</a></li>
  <li class="active">一级菜单列表</li>
</ol>


 
 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getSuperMenuList.html" method="post">  
    
    	<div class="col-md-4">
	
	<div class="input-group">
	
          <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"   id="conditions['name']" name="conditions['name']" >
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="输入菜单名称模糊查询"   id="conditions['name']" name="conditions['name']"   >
			   </c:otherwise>  
		</c:choose>
	
         
	
	<span class="input-group-btn">
	   <button class="btn btn-default" type="submit"   id="btnsearch">查询</button>
	</span>
	</div>
	</div>
 
 
  <div class="col-md-2">
        
        	<a href="manager/addSuperMenuInit.html"  class="btn btn-success"  style="margin-left:60px; " >增加一级菜单</a>
        </div>
        
 
 </form>
</div> 

 
<!--  {"currPage":1,"totalPages":1,"params":"","conditions":{},"pageSize":10,"totalRecords":6,"isHavePrePage":false,"isHaveNextPage":false,
     "pageDatas":[{"id":1,"name":"系统设置","description":"账号和角色","isuse":true,"icopath":"components/menu01/images/left/syetem_management.png","icopathopen":"components/menu01/images/left/syetem_management.png"},
	                      {"id":2,"name":"商品管理","description":"管理商品","isuse":false,"icopath":"components/menu01/images/left/app.png","icopathopen":"components/menu01/images/left/app.png"},
	                      {"id":6,"name":"菜单管理","description":"用来管理 一级菜单 和二级菜单","isuse":true,"icopath":"components/menu01/images/left/custom.png","icopathopen":"components/menu01/images/left/custom.png"}
                         ]
  } -->
	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
	        	<th>排序序号</th>
				<th>菜单名称</th>
				<th>菜单说明</th>
				<th>是否可见</th>
				<th>默认图片地址</th>
				<th>选中图片地址</th>
				<th>操作</th>
				</tr>
			</thead>

			<tbody>
			 
				<c:forEach var="menu" items="${pages.pageDatas}">
					<tr id="tr_${menu.id }">
					<td> ${menu.sortid} </td>
						<td> ${menu.name} </td>
						<td> ${menu.description}</td>
						
                             <td><c:choose>
								<c:when test="${menu.isuse}">
									<input type="checkbox" checked onclick="changFlag(this,${menu.id})">显示
								  </c:when>

								<c:when test="${! menu.isuse}">
									<input type="checkbox"  	onclick="changFlag(this,${menu.id})">显示
								  </c:when>
 
							</c:choose></td>
							
							
						<td>${menu.icopath}</td>
						<td>${menu.icopathopen}</td>
							 
							 <td>
						  
						    <a href="manager/updateSuperMenuInit.html?id=${menu.id }"> 修改 </a>  
						 
							<a href="javascript:void(0)" onclick="deleteById(${menu.id})"> 删除 </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}"  url="${pageContext.request.contextPath}/manager/getSuperMenuList.html?conditions['name']=${pages.conditions.name}"  />    
 
           </c:if>
				 
 
  </div>
	 

</body>





<script>



//传递2个参数 一个是对象本身 一个是 内容ID
function changFlag(e, id) {
	/* var ctrl = $("#" + id); */

	var isUse = 0;

	if (e.checked == true) {
		isUse = 1;
	} else {
		isUse = 0;
	}

	$.ajax({
		type : 'POST',
		url : '${pageContext.request.contextPath}/manager/updateSuperMenuFlag.html',
		data : {
			"id" : id,
			"flag" : isUse
		},

		dataType : 'json', //要求服务器返回 json对象 而不是字符串
		success : function(data) {

			if (data.msg == "updateOK") {

				layer.msg('更新成功');
			} 
			
			else {
				layer.msg('未更新');
			}

		},
		error : function() {

			layer.msg("请求出错，可能连接不上服务器");
		}

	})

}




	/*    通过ajax 提交删除请求 ，根据返回结果 删除本地 表格里的数据，实现无刷新删除 */

	function deleteById(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteSuperMenuById.html',
			data : {"id" : id},
			dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(data) {

				if (data.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				} else if (data.msg=="error") {
					layer.msg('有二级菜单绑定到本菜单，<br>所以不能删除本条数据！');
				}
				else {
					layer.msg('删除失败');
				}
			},
			error : function() {
				alert("出错");
			}

		})
	}
</script>



</html>
