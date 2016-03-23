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
<title>二级菜单列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">菜单管理</a></li>
  <li class="active">二级菜单列表</li>
</ol>


 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getSecondMenuList.html" method="post"  class="form-inline">  
 
      <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['superMenuId']" >一级菜单:</label>
		  <select  id="superMenuId" onchange="superMenuChanged( )" name="conditions['superMenuId']"  class="  form-control"  style="margin-left:10px;width:120px">
              
                <option value=0>--全部--</option>
          
                   <c:forEach var="item" items="${superMenuList}">
                   
                      <option value=${item.id}>${item.name}</option>
                      
                   </c:forEach>  
            </select>
 
       </div>
       
       
       
             <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['secondMenuId']" >二级菜单:</label>
		   <!--  由于 jquery 取 conditions['secondMenuId'] 不好取，所以这里 id 和 name 取的不一样。  springmvc根据name取值， jquery根据 id取值 -->
		  <select  id="secondMenuId"   name="conditions['secondMenuId']" class="  form-control"  style="margin-left:10px;width:120px">
              
                <option value=0>--全部--</option>
          
                  <c:forEach var="secItem" items="${secondMenuList}">
                   
                      <option value="${secItem.id}">${secItem.name}</option>
                      
                   </c:forEach>  
            </select>
 
       </div>
       
       
            
<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['name']" >菜单名称:</label>
          <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"   id="conditions['name']" name="conditions['name']" >
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="输入菜单名称模糊查询"   id="conditions['name']" name="conditions['name']"   >
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">菜单名称:</label>
          
           <input type="submit"  value="查询"  class="btn btn-primary">
              
	</div> 
	
	
 
<a href="manager/addSecondMenuInit.html"  class="btn btn-success"  style="margin-left:60px; " >增加二级菜单</a>
  
</form>

 
 
 
 
 
  

 
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
	        
	        	<th>上级菜单</th>
				<th>菜单名称</th>
				 <th>页面url</th>
				 <th>排序序号</th>
				<th>菜单说明</th>
					
				<th>是否可见</th>
			 
				<th>操作</th>
				</tr>
			</thead>

			<tbody>
			 
				<c:forEach var="vo" items="${pages.pageDatas}">
					<tr id="tr_${vo.right.id }">
				
					      	<td> ${vo.rightType.name} </td>
						<td> ${vo.right.name} </td>
								 	<td> ${vo.right.url} </td>
								 	
								 		<td> ${vo.right.sortid} </td>
								 		
						<td> ${vo.right.description}</td>
						
                             <td><c:choose>
								<c:when test="${vo.right.isuse}">
									<input type="checkbox" checked onclick="changFlag(this,${vo.right.id})">显示
								  </c:when>

								<c:when test="${! vo.right.isuse}">
									<input type="checkbox"  	onclick="changFlag(this,${vo.right.id})">显示
								  </c:when>
 
							</c:choose></td>
							
							
						 
							 
							 <td>
						  
						    <a href="manager/updateSecondMenuInit.html?id=${vo.right.id }"> 修改 </a>  
						 
							<a href="javascript:void(0)" onclick="deleteById(${vo.right.id})"> 删除 </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getSecondMenuList.html?conditions['name']=${pages.conditions.name}&conditions['superMenuId']=${conditions.superMenuId }&conditions['secondMenuId']=${conditions.secondMenuId }"  />    
 
           </c:if>
				 
 
  </div>
	 

</body>





<script>

 //初始化  2个下拉菜单
function initSelectOptions()
{
	
	$("#superMenuId").val(${pages.conditions.superMenuId});
	
	//后台 需要先根据 父级id 把二级 列表 返回到前台，才能生效，否则二级菜单显示的是全部， 不能保留查询条件
	$("#secondMenuId").val(${pages.conditions.secondMenuId});
 }
 
initSelectOptions();


/*  响应一级菜单 changed事件 */
function superMenuChanged( )
{
 	
	  var op = $("#superMenuId option:selected");
	  var superMenuId = op.val();
	  
	  if(superMenuId==0)
		  {
		    
		    $("#secondMenu").html("<option value=0>--全部--</option>");
		   return;
		  
		  }
	  else
		  {
		  $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/manager/getSecondMenuListBySuperId.html',
				data : {"superMenuId" : superMenuId},
			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
				success : function(arr) {
	 
					 
					  
				    var secondMenuList="<option value=0>全部</>";
				    
				    var length = arr.length;
				    for(var i = 0; i < length; i ++){
				    	 
				    		secondMenuList = secondMenuList + "<option value="+arr[i].id+">"+arr[i].name+"</option>";
				    }
				   $("#secondMenuId").html(secondMenuList);
					 
				},
				error : function() {
					alert("出错");
				}

			})
		  
		  
		  }
	  
	
	 
	
}


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
		url : '${pageContext.request.contextPath}/manager/updateSecondMenuFlag.html',
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
			url : '${pageContext.request.contextPath}/manager/deleteSecondMenuById.html',
			data : {"id" : id},
			dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(data) {

				if (data.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				}  
				else if (data.msg=="hasRoleBind") {
					layer.msg('有角色通过角色权限设置绑定到了本菜单，<br>所以不能删除本条数据！');
				}
				else if (data.msg=="hasUserBind") {
					layer.msg('有用户通过用户权限功能绑定了本菜单，<br>所以不能删除本条数据！');
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
