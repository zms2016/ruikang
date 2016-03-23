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
<title>理财用户列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 <script src="js/zmsTools.js"></script>
 
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">融资客户</a></li>
  <li class="active">融资客户列表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getRongziList.html" method="post">  
    
    	<div class="col-sm-4">
	
	<div class="input-group">
	
          <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"    id="name" name="conditions['name']"  maxlength="20" >
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据融资客户姓名模糊查询"   id="name"  name="conditions['name']"  maxlength="20"  >
			   </c:otherwise>  
		</c:choose>
 
		<span class="input-group-btn">
		   <button class="btn btn-primary" type="button"   id="btnsearch">查询</button>
		</span>
	</div>
	
	</div>
 
 	 <div class="col-sm-2">
        
        	<a href="manager/addRongziInit.html"  class="btn btn-success"  style="margin-left:120px; "  >增加融资客户</a>
        </div>
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				<th>用户编号</th>
				<th>客户姓名</th>
				<th>手机号码</th>
				<th>身份证</th>
				 <th>联系地址</th>
					 
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
 
			 
				<c:forEach var="rongzi" items="${pages.pageDatas}">
				
				
					<tr id="tr_${rongzi.id }">
						<td><c:out value="${rongzi.id}" /></td>

						<td><c:out value="${rongzi.name}" /></td>
						<td><c:out value="${rongzi.mobilenum}" /></td>
										<td><c:out value="${rongzi.card}" /></td>
														 <td><c:out value="${rongzi.address}" /></td>
						<%-- 	<td>  <fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日" /> </td> --%>
						<td>
						 
						    <a href="manager/updateRongziInit.html?id=${rongzi.id}"> 修改 </a>  
						    
							<a href="javascript:void(0)" onclick="deleteById(${rongzi.id})"> 删除 </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getRongziList.html?conditions['name']=${pages.conditions.name}"  />    
 
           </c:if>		 

	
  </div>
	 

</body>


<script>


$("#btnsearch").click(function(){
	
	  if (checkChar($("#name").val()) )
			  {
		          layer.msg('搜索条件含有非法字符！');
		          return false;
			  }
	  
	
    
	  $("#searchForm").submit();
	
});


	/*    通过ajax 提交删除请求 ，根据返回结果 删除本地 表格里的数据，实现无刷新删除 */

	function deleteById(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteRongziById.html',
			data : {"id" : id},
		 
		       dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(msg) {

				if (msg.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				}else if (msg.msg=="hasBanks"){
					
					layer.msg('不能删除融资客户！因为有银行账户绑定了它。<br>请先删除银行账户');
				}
               else if (msg.msg=="hasInvest"){
					
					layer.msg('不能删除融资客户！因为有融资项目使用了它');
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
