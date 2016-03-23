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

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<base href="<%=basePath%>">


   <!--   bootstrap 样式  默认需要-->
<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

 
 

   <!-- jquery和bootstrap 默认都需要 -->
<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 
 <script src="js/zmsTools.js"></script>
 <script src="components/layer2/layer/layer.js"></script> 
 


</head>
<body>

		         <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">系统设置</a></li>
    <li><a href="javascript:void(0)">角色管理</a></li>
  <li class="active">角色列表</li>
</ol>

	<div class="row" style="margin-top:20px;margin-left:20px">
	
	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getRoleList.html" method="post">  
	<div class="col-lg-4">
	
	<div class="input-group">
	
          <c:choose>
			    <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"   id="name"   name="conditions['name']"    maxlength="20">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据角色名称或者 角色描述模糊查询"   id="name"  name="conditions['name']"    maxlength="20">
			   </c:otherwise>  
		</c:choose>
	

	
	<span class="input-group-btn">
	   <button class="btn btn-default" type="button"   id="btnsearch">查询</button>
	</span>
	</div>
	</div>
	
	
	 <div class="col-sm-2">
        <a href="javascript:void(0)"  class="btn btn-success"  style="margin-left:420px;margin-top:5px"  id="addrole">增加角色</a>
        </div>
        
        
	</form>
	
	</div>



	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:20px">
	<table class="table table-striped  table-bordered table-hover">
	<thead   >
	<tr  style="background-color:#dcdcdc; ">
	<th  > 编号</th>
	<th>角色名称</th>
	<th>角色描述</th>
 
	<th>操作</th>
	</tr>
	</thead>

	<tbody id="tbody">

                <c:forEach var="role"  items="${pages.pageDatas}">
					<tr id="tr_${role.id }">
						<td><c:out value="${role.id}" /></td>
						<td><c:out value="${role.name}" /></td>
						<td><c:out value="${role.description}" /></td>
                         <td>
							<a href="javascript:void(0)"  onclick="editrole(${role.id }) ">编辑 </a>
							
							<a href="javascript:void(0)"   onclick="setRoleRights(${role.id })  " >设置权限 </a>
							
						 	 <a href="manager/getUserListByRoleId.html?roleId=${role.id }"  >管理角色人员 </a>  
								
							 <a href="javascript:void(0)"   onclick="deleterole(${role.id})" >删除</a>
							 
							</td>
					</tr>
				</c:forEach>


	</tbody>

	</table>

    
	
	
		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
     <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
      url="${pageContext.request.contextPath}/manager/getRoleList.html?conditions['name']=${pages.conditions.name}"  />    
 
     </c:if>
     
   
      





        <!-- 开始  增加 div -->
      <div id="addrolediv" > </div>
	 
          <!-- 编辑角色 -->
      <div id="editrolediv" > </div>
      
        <!--  设置角色的权限 -->
       <div id="setRoleRights"></div>


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


</script>




<script type="text/javascript">

//定义 增加角色的层 变量，以便可以通过该变量来操作层 ，比如关闭它
var addlay,editlay,setRoleRightLay;


/* 增加角色按钮 */
 
$("#addrole").on('click', function () {
	
	  $.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/addRoleInit.html',
	      data : {},
	      success : function(data) {
	      
	    	  
	    	    $("#addrolediv").html(data);
	    	   //自定页
	    	  addlay= layer.open({
	    		   type: 1,
	    		   title:false,
	    		   closeBtn: true, // 显示关闭按钮
	    		   shift: 1,
	    		   area:['800px','460px'],
	    		   shadeClose: false, //点击窗口意外区域 不响应
	    		   content: $("#addrolediv")
	    	   });
	    	   
	    	   
	      },
	      error : function() {
	        alert("对不起，出现错误!");
	      }
	    });
	
	
	

})


/* 修改角色 */
 
 function editrole(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/updateRoleInit.html',
			data : {"id" : id},
			 
		 	success : function(data) {
		 	 
		 		  
	    	    $("#editrolediv").html(data);
	    	    
	    	    editlay= layer.open({
	    		   type: 1,
	    		   title:false,
	    		   closeBtn: true, // 显示关闭按钮
	    		   shift: 1,
	    		   offset: ['15px'],
	    		   area:['800px','460px'],
	    		   shadeClose: false, //点击窗口意外区域 不响应
	    		   content: $("#editrolediv")
	    	   });
	    	   
             
			},
			error : function() {
				alert("请求出错，可能连接不上服务器");
			}

		})
	}
 

/* 删除角色 */
 function deleterole(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteRoleById.html',
			data : {"id" : id},
		 
			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
			success : function(data) {
				
                  
                   
				if (data.msg== "ok") {
					$("#tr_" + id).remove();
					layer.msg('删除成功');
					return;
				} else  if(data.msg="hasUser"){
					layer.msg('该角色正在使用中，不允许删除！');
					return;
				}else
					{
					layer.msg('删除失败！');
					}
			},
			error : function() {
				alert("请求出错，可能连接不上服务器");
			}

		})
	}

 
 
 
/* 设置角色的权限 */
 
 function setRoleRights(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/setRoleRightsInit.html',
			data : {"roleid" : id 
			           },
			 
		 	success : function(data) {
		 	 
		 		  
	    	    $("#setRoleRights").html(data);
	    	    
	    	     setRoleRightLay= layer.open({
	    		   type: 1,
	    		   title:false,
	    		   closeBtn: true, // 显示关闭按钮
	    		   shift: 1,
	    		   offset: ['15px'], //只设置一个 表示 top 设置2个，表示 top left
	    		   area:['600px','760px'],
	    		   shadeClose: false, //点击窗口意外区域 不响应
	    		   content: $("#setRoleRights")
	    	   });
	    	   
             
			},
			error : function() {
				alert("请求出错，可能连接不上服务器");
			}

		})
	}
	
	
</script>


 


</html>