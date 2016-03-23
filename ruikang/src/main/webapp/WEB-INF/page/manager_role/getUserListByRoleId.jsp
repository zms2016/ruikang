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
<title>绑定角色</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

 <script src="js/zmsTools.js"></script>
 
 
 
 <script src="components/layer2/layer/layer.js"></script>
 
 <style>
   .tda a{
   
     color:#E4E4E4
   }
 </style>
</head>
 
<body>
 
 		         <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">系统设置</a></li>
    <li><a href="javascript:void(0)">角色管理</a></li>
  <li class="active">绑定角色 </li>
</ol>

 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getUserListByRoleId.html" method="post">  
    
    	<div class="col-lg-4">
	
	<div class="input-group">
	
          <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"   id="name"  name="conditions['name']"   maxlength="20" >
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据登录名或真实姓名模糊查询"   id="name"  name="conditions['name']"   maxlength="20" >
			   </c:otherwise>  
		</c:choose>
	
           <input type="hidden" name="roleId" id="roleId"  value="${roleId }">
	
	<span class="input-group-btn">
	   <button class="btn btn-default" type="button"   id="btnsearch">查询</button>
	</span>
	</div>
	</div>
 
 </form>
</div> 

<!--   {"currPage":1,"totalPages":1,"pageSize":10,"totalRecords":4,"isHavePrePage":false,"isHaveNextPage":false,
    "pageDatas":[
                        
                        {"user":{"id":178,"username":"wry","birthday":"Oct 7, 2015 12:00:00 AM","address":"","pic":"39128fb5-b784-4e1d-8008-80150916d963.jpeg","password":"DC15A999CF8F2FF602A4A105343645FB","realname":"王柔懿"},"hasBinded":false,"roleId":91},
                        {"user":{"id":179,"username":"hm","birthday":"Sep 30, 2015 12:00:00 AM","address":"","pic":"048980d0-03af-4697-b11f-e9284640f414.jpeg","password":"4DD19F1ECF2524E250100CFC4C1E2C0C","realname":"胡梅"},"hasBinded":false,"roleId":91},
                        {"user":{"id":181,"username":"zys","address":"湖南的？","password":"202CB962AC59075B964B07152D234B70","realname":"雷锋 222"},"hasBinded":true,"roleId":91}
                      ]
    } -->

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				<th>用户编号</th>
				<th>当前角色</th>
				<th>登录账号</th>
				<th>真实姓名</th>
		    	 <th>是否拥有角色</th>
				<th>操作</th>
				</tr>
			</thead>

			<tbody>
			
			 
				<c:forEach var="vo"  items="${pages.pageDatas}">
				
				
					<tr >
						<td><c:out value="${vo.user.id}" /></td>
                        <td>${role.name }</td>
						<td><c:out value="${vo.user.username}" /></td>
						<td><c:out value="${vo.user.realname}" /></td>
						 
						 <td  id="td_${vo.user.id }"> 
						 <c:choose>
						            <c:when test="${vo.hasBinded }">
						                   <span style="color:#31822E">已分配  </span>    
						            </c:when>
						             
						           <c:otherwise>
						                 <span style="color:#B9B9B9">    未分配 </span>
						           
						           </c:otherwise>
						        
						        </c:choose>
						 
						 </td>
						 
						 <td id="tda_${vo.user.id }"  class="tda"   >
						        <c:choose>
						            <c:when test="${vo.hasBinded }">
						             <a href="javascript:void(0)"  style="float:left;margin-left:10px"  onclick="unBindRole(${vo.user.id},${roleId}  )" class="btn btn-danger"> 解除绑定 </a> 
						            </c:when>
						             
						           <c:otherwise>
						              <a href="javascript:void(0)"  style="float:left;margin-left:100px"  onclick="bindRole(${vo.user.id},${roleId}   )"  class="btn btn-primary"> 绑定角色 </a> 
						           
						           </c:otherwise>
						        
						        </c:choose>
						         
						        
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
			<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
     <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
      url="${pageContext.request.contextPath}/manager/getUserListByRoleId.html?roleId=${roleId}&conditions['name']=${pages.conditions.name}"  />    
 
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

	function unBindRole( userId,roleId) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/unBindRole.html',
			data : {"userId" : userId,
				       "roleId": roleId
			         },
			         dataType:'json',  //要求服务器返回 json对象 而不是字符串
			success : function(data) {
				 
				if (data.msg == "unBindOk") {
				     
				     $("#td_"+userId).html(" <span style=\"color:#B9B9B9\">    未分配 </span>  ");
				     $("#tda_"+userId).html("  <a href=\"javascript:void(0)\"  style=\"float:left;margin-left:100px\"  onclick=\"bindRole("+userId+",${roleId}   )\" class=\"btn btn-primary\"> 绑定角色 </a>  ");
				     
					//layer.msg('解除成功');
				} else {
					layer.msg('解除失败');
				}
			},
			error : function() {
				alert("出错");
			}

		})
	}
	
	
	
	function bindRole( userId,roleId) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/bindRole.html',
			data : {"userId" : userId,
				       "roleId": roleId
			         },
			         dataType:'json',  //要求服务器返回 json对象 而不是字符串
			success : function(data) {

				 
				if (data.msg == "bindOk") {
				     
				     $("#td_"+userId).html("     <span style=\"color:#31822E\">已分配  </span>     ");
				     
				     $("#tda_"+userId).html("  <a href=\"javascript:void(0)\"  style=\"float:left;margin-left:10px\"  onclick=\"unBindRole("+userId+",${roleId}   )\" class=\"btn btn-danger\"> 解除绑定 </a>  ");
				//	layer.msg('绑定成功');
				} else {
					layer.msg('绑定失败');
				}
			},
			error : function() {
				alert("出错");
			}

		})
	}
	
	
</script>



</html>
