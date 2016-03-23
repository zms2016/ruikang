<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="page" uri="http://zms.20032015.com/pager" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 

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
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active">理财客户列表</li>
</ol>

<div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getVipList.html" method="post"  class="form-inline">  
 	
 
	    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="name" >理财客户:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"    id="name" name="conditions['name']"   maxlength="20">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="理财客户名称精确查询"   id="name"  name="conditions['name']"   maxlength="20">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
 
     <c:if test="${fn:length(managers)>1}">
     
       	       <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="manager" >理财经理:</label>
 
		  <select  id="manager"    name="conditions['manager']"  class="  form-control"  style="margin-left:10px;width:120px">
              
                <option value="0">--全部--</option>
                 
                      <c:forEach var="manager" items="${managers}">
                             <c:choose>
                                   <c:when test="${manager.id==pages.conditions.manager }">
                                      <option value="${manager.id}" selected="selected">${manager.realname}</option>
                                   </c:when>
                                   <c:otherwise>
                                      <option value="${manager.id}">${manager.realname}</option>
                                   </c:otherwise>
                             </c:choose>
    
                       </c:forEach>     
							               
            </select>
 
       </div>
     
     </c:if>
               
	
 
 
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary" type="submit"   id="btnsearch">查询</button>
              
              
              
              	<a href="manager/addVipInit.html"  class="btn btn-success"  style="margin-left:120px; " >增加客户</a>
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
			<!-- 	<th>客户类型</th> -->
				<th>理财经理</th>
					<th >联系地址</th>
					 
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
 
			 
				<c:forEach var="vo" items="${pages.pageDatas}">
				
				
					<tr id="tr_${vo.vip.id }">
						<td><c:out value="${vo.vip.id}" /></td>

						<td><c:out value="${vo.vip.name}" /></td>
						<td><c:out value="${vo.vip.mobilenum}" /></td>
										<td><c:out value="${vo.vip.card}" /></td>
													<%-- 	<td><c:out value="${vo.level}" /></td> --%>
																		<td><c:out value="${vo.managerName}" /></td>
						<td style="max-width:600px"><c:out value="${vo.vip.address}" /></td>
						<%-- 	<td>  <fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日" /> </td> --%>
						<td>
						  <%--   <a href="manager/getVipInfo.html?id=${vo.vip.id }"> 详情 </a> --%>
						    <a href="manager/updateVipInit.html?id=${vo.vip.id  }"> 修改 </a>  
						    
							<a href="javascript:void(0)" onclick="deleteById(${vo.vip.id })"> 删除 </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getVipList.html?conditions['name']=${pages.conditions.name}&conditions['manager']=${pages.conditions.manager }"  />    
 
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
			url : '${pageContext.request.contextPath}/manager/deleteVipById.html',
			data : {"id" : id},
		 
		       dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(msg) {

				if (msg.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				} else if (msg.msg=="hasContract"){
					layer.msg('该理财客户已经有理财合同，所以不能删除！');
				}
				else
					{
					layer.msg('删除失败‘');
					}
			},
			error : function() {
				alert("出错");
			}

		})
	}
</script>



</html>
