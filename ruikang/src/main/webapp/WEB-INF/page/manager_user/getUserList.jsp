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
<title>用户列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

 <script src="js/zmsTools.js"></script>
 
 
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">用户管理</a></li>
  <li class="active">用户列表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getUserList.html" method="post">  
    
    	<div class="col-sm-4">
	
	<div class="input-group">
	
          <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"   id="name" name="conditions['name']"  maxlength="40">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据登录名或真实姓名模糊查询"   id="name" name="conditions['name']"    maxlength="40">
			   </c:otherwise>  
		</c:choose>
	

	
	<span class="input-group-btn">
	   <button class="btn btn-default" type="button"   id="btnsearch">查询</button>
	</span>
	
	
	</div>
	</div>
        
        <div class="col-sm-2">
        <a href="manager/addUserInit.html"  class="btn btn-success"  style="margin-left:420px;margin-top:5px">增加用户</a>
        </div>
 
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				<th>用户编号</th>
				<th>登录账号</th>
				<th>真实姓名</th>
				<th>所属部门</th>
				<!-- <th>出生日期</th> -->
				<th>账号状态</th>
				<th>操作</th>
				</tr>
			</thead>

			<tbody>
			
			 
				<c:forEach var="uservo" items="${pages.pageDatas}">
				
				
					<tr id="tr_${uservo.user.id }">
						<td><c:out value="${uservo.user.id}" /></td>

						<td><c:out value="${uservo.user.username}" /></td>
						<td><c:out value="${uservo.user.realname}" /></td>
						<td><c:out value="${uservo.departmentName }" /></td>
						
						<td  id="td_${uservo.user.id }">  
						       <c:choose>
						          <c:when test="${ uservo.user.isuse<1}">
						            正常
						          </c:when>
						       
						            <c:otherwise>
						                      已停用
						            </c:otherwise>
						       </c:choose>
						   </td>
						<%-- 	<td>  <fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日" /> </td> --%>
						<td>
						    <%-- <a href="manager/getUserInfo.html?id=${user.id }"> 详情 </a> --%>
						    <a href="manager/updateUserInit.html?id=${uservo.user.id }"> 修改 </a>  
						     <a href="manager/setUserRightsInit.html?id=${uservo.user.id }"> 定制权限</a>  
					 		 
					 		 
                               
                               
                                 <c:choose>
						          <c:when test="${ uservo.user.isuse<1}">
						            <span id="a_${uservo.user.id }">  <a href="javascript:void(0)" onclick="changeUserFlag(${uservo.user.id},0)" >停用 </a>    </span>      
						          </c:when>
						       
						            <c:otherwise>
						                  <span id="a_${uservo.user.id }">  <a href="javascript:void(0)" onclick="changeUserFlag(${uservo.user.id},1)" >启用 </a>    </span>      
						                      
						            </c:otherwise>
						       </c:choose>
						       
						       
                          
						   <c:if test="${loginname=='super'}" > 
                                      <a href="javascript:void(0)" onclick="deleteuser(${uservo.user.id})"> 删除 </a>
                          </c:if> 
						 
						   <c:choose>
						      <c:when test=""></c:when>
						      <c:otherwise></c:otherwise>
						   </c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
     <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
      url="${pageContext.request.contextPath}/manager/getUserList.html?conditions['name']=${pages.conditions.name}"  />    
 
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

	function deleteuser(id) {
		
		
		  if (id==1)
			  {
			   layer.msg('系统超级管理员不能被删除！');
			  return;
			  }

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteUser.html',
			data : {"id" : id},
			success : function(msg) {

				if (msg == "deleteok") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				}  else if(msg=="delAdmin") {
					layer.msg('不能尝试去把超级管理员给删了！');
				}
				  else if(msg=="hasVip") {
						layer.msg('该账号已经存在理财客户,不能被删除！');
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
	
	
	
	function changeUserFlag(id,flag) {
		 
		 var textString;
		 var  aString;
		 if ( flag==0)
			 {
			 textString="停用";
			 aString="已停用";
			 }
		 else{
			 textString=" 正常";
			 aString="已启用";
		 }
		
		  if (id==1)
			  {
			   layer.msg('系统超级管理员不能被修改！');
			  return;
			  }

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/changeUserFlag.html',
			data : {"userId" : id,"flag":flag},
			success : function(msg) {

				if (msg == "changeOk") {
					$("#td_" + id).text(textString);
					$("#a_" + id).text(aString);
					layer.msg('修改成功');
				}  else if(msg=="delAdmin") {
					layer.msg('不能修改管理员！');
				}
				  else if(msg=="noChange") {
						layer.msg('修改失败！');
					}
				
				
				else {
					layer.msg('修改失败');
				}
			},
			error : function() {
				alert("出错");
			}

		})
	}
	
	
</script>



</html>
