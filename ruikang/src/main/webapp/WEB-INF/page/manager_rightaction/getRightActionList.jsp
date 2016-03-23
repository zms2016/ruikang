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


<script src="components/layer2/layer/layer.js"></script>



</head>
<body>

     
    
  
	<form name="contentForm" method="post"   action="manager/getRightActionList.html"   
	          style="    background-color: #EAEAEA;  height: 100%; margin: 0; padding: 0;  overflow: hidden;  color: #4C4C4C;">

		<div class="form-group form-inline" style="margin:20px">
			<strong style="font-size:1.2em"> 一级菜单:</strong>
			 <select  id="parentId" onchange="parentIdchanged( )" name="parentId" 	class="  form-control" style="margin-left:10px">
				<option value="0">-全 部-</option>

				<c:forEach var="item" items="${typeList}">

					<option value="${item.id}">${item.name}</option>

				</c:forEach>
			</select> 
			
			<strong style="font-size:1.2em;margin-left:10px"> 二级菜单:</strong>
			<select id="typeid" name="typeid" class=" form-control">
				<option value=0>-全 部-</option>
				<c:forEach var="childItem" items="${childList}">
					<option value="${childItem.id}">${childItem.name}</option>
				</c:forEach>

			</select> 
			
			<label for="filetitle" style="margin-left:10px"> 
			<strong style="font-size:1.2em"> 路径字符串模糊查询： </strong>
				</label> 
				<input type="text"  class="form-control" id="filetitle"   name="filetitle" style="width:300px"   value="${pages.conditions.filetitle}"> 
				<input type="submit"  name="button" value="查 询" class="btn btn-default" />
		</div>
 

	</form>
	  
	
	
	  
	  
	  
	  <!--  新增数据 -->
	  <div class="form-group form-inline" style="margin:20px"   >
			<strong style="font-size:1.2em"> 一级菜单:</strong>
			 <select  id="addFirstMenu" onchange="FirstMenuChange( )" name="addFirstMenu" 	class="  form-control" style="margin-left:10px">
			 

				<c:forEach var="item" items="${typeList}">

					<option value="${item.id}">${item.name}</option>

				</c:forEach>
			</select> 
			
			<strong style="font-size:1.2em;margin-left:10px"> 二级菜单:</strong>
			<select id="addSecMenu" name="addSecMenu" class=" form-control">
				<option value=0>-全 部-</option>
				<%-- <c:forEach var="childItem" items="${childList}">
					<option value="${childItem.id}">${childItem.name}</option>
				</c:forEach> --%>

			</select> 
			
			     <label for="action" style="margin-left:10px"> 
			         <strong style="font-size:1.2em"> 路径 ：</strong>
				</label> 
				<input type="text"  class="form-control" id="action"   name="action"   style="    width: 280px;" /> 
				
		       <label for="description" style="margin-left:10px"> 
			         <strong style="font-size:1.2em">  说明： </strong>
				</label> 
				<input type="text"  class="form-control" id="description"   name="description"    style="    width: 140px;"  /> 
				
				
				<input type="button"  name="button" value="增加" class="btn btn-success "  onclick="addRightAction()"/>
		</div>
		
		</div>
	   

	<div style="margin-left:16px; margin-top:10px;margin-bottom:90px;margin-right:20px">
		<table class="table table-striped  table-bordered table-hover">
			<thead>
				<tr style="background-color:#efefef; ">
				 
				 	<th>路径url</th>
				 	<th>路径说明</th>
					<th>所属模块</th>
					
				
 
					<th>操作</th>
				</tr>
			</thead>

 
<!-- {"currPage":1,"totalPages":1,"params":"","conditions":{},"pageSize":10,"totalRecords":2,"isHavePrePage":false,"isHaveNextPage":false,"
pageDatas":[{"rightActions":{"id":2,"rightid":2,"action":"manager/getRoleList.html","description":"角色管理"},"rights":{"id":2,"typeid":1,"name":"角色管理","description":"管理角色"}},
{"rightActions":{"id":12,"rightid":13,"action":"manager/user.html","description":"发展"},"rights":{"id":13,"typeid":3,"name":"发布内容","description":"用来发布内容"}}]} -->

			<tbody id="tbody">
				<c:forEach var="item" items="${pages.pageDatas}">
					<tr id="tr_${item.rightActions.id }">
						 
						      <td><c:out value="${item.rightActions.action}" /></td>
						        <td><c:out value="${item.rightActions.description}" /></td>
			         	 <td>  ${item.rights.name }</td>
				 	   
						<td>
						<a href="javascript:void(0)"  onclick="showUpdateWindow(${item.rightActions.id},${item.rights.id })">编辑 </a> 
						<a href="javascript:void(0)"  onclick="deleteById(${item.rightActions.id})">删除</a></td>

					</tr>
				</c:forEach>


			</tbody>

		</table>

		
		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
     <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
      url="${pageContext.request.contextPath}/manager/getRightActionList.html?params=${pages.params}&parentId=${pages.conditions.firstMenuId}&typeid=${pages.conditions.secMenuId}&filetitle=${pages.conditions.filetitle}"  />    
 
     </c:if>
  
  


	</div>
	
	
	
	
	
	
	
	
  <!--  编辑 权限路径 弹框 -->
	
	<div class="modal fade"  id="myModal">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><strong>修改 权限路径</strong></h4>
            </div>

            <div class="modal-body" style="color: #656464">


                <div class="form-group form-inline" style="margin:20px"   >
                    <strong style="font-size:1.2em"> 一级菜单:</strong>
                    <select  id="upFirstMenu" onchange="upFirstMenu( )" name="upFirstMenu" 	class="  form-control" style="margin-left:10px">

                         <c:forEach var="item" items="${typeList}">
					          <option value="${item.id}">${item.name}</option>
				          </c:forEach>
				          
                    </select>

                    <strong style="font-size:1.2em;margin-left:10px"> 二级菜单:</strong>
                    <select id="upSecMenu" name="upSecMenu" class=" form-control">
                    <option value="0">全部</option>

                    </select>

                    <br>

                    <label for="upAction" style="margin-left:10px;margin-top: 10px">
                        <strong style="font-size:1.2em"> 路 径:</strong>
                    </label>
                    <input type="text"  class="form-control" id="upAction"   name="upAction"   style="    width: 420px;" />

                     <br>

                    <label for="upDescription" style="margin-left:10px; margin-top: 10px">
                        <strong style="font-size:1.2em"> 说 明:</strong>
                    </label>
                    <input type="text"  class="form-control" id="upDescription"   name="upDescription"    style="    width:420px;"  />



                </div>




            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  id="subSubMit" data-dismiss="modal">提交</button>
            </div>

        </div><!-- /.modal-content -->

    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->






</body>


<script>

  //定义全局变量
  var  rightActionId=0;

</script>

 <!-- 本段 javaacript用来处理 编辑 路径 -->
<script>

 //点击 列表 的编辑按钮就开始弹框
 
 function showUpdateWindow(id,rightid)
 {
	   
	   rightActionId=id;
	   
		   
	    $.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/getRightActionVoById.html',
			data : {
				"id" : id
			},
			dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(arr) {

				  $("#upAction").val( arr.rightActions.action)
				  $("#upDescription").val( arr.rightActions.description);
				  
			    	$("#upFirstMenu").val(arr.rights.typeid);
			     
			    	upFirstMenu( rightid);
			    	 
			    	 
					
			    $("#myModal").modal({
			        keyboard: false,  //按esc  不关闭
			        backdrop: 'static' //点空白处 不关闭
			    }) 
                    
			     
			},
			error : function() {
				alert("出错");
			}

		})
 
 }
 
 

 
/* 修改 弹出框的的菜单联动 */
function upFirstMenu(rightid) {
	var op = $("#upFirstMenu option:selected");
	var upFirstMenu = op.val();

	 
	if (upFirstMenu == 0) {
		$("#upSecMenu").html("<option value=0>请选择！</option>");

	}

	else {
		 
		$.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath}/manager/getSecMenuByFartherId.html',
					data : {
						"parentId" : upFirstMenu
					},
					dataType : 'json', //要求服务器返回 json对象 而不是字符串
					success : function(arr) {

						//   var arr=eval(data);

						var  upSecMenu="" ;
						var length = arr.length;
						for (var i = 0; i < length; i++) {
							 
							    
							     if (arr[i].id==rightid)
							    	 {
							    	 upSecMenu = upSecMenu + "<option value='"+arr[i].id+"' selected>" + arr[i].name + "</option>";
							    	 }
							     else
							    	 {
							    	 upSecMenu = upSecMenu + "<option value='"+arr[i].id+"' >" + arr[i].name + "</option>";
							    	 }
								
						}
					 
					 
						$("#upSecMenu").html(upSecMenu);

					},
					error : function() {
						alert("出错");
					}

				})

	}
}


  //提交修改
$("#subSubMit").click(function(){
	  
	 
	
	$.ajax({
		type : 'POST',
		url : '${pageContext.request.contextPath}/manager/updateRightActionAction.html',
		data : {
			  "id":rightActionId,
			  "rightid": $("#upSecMenu").val(),
			   "action": $("#upAction").val(),
		       "description":$("#upDescription").val()
		
		},
		dataType : 'json', //要求服务器返回 json对象 而不是字符串
		success : function(data) {

			 
			if (data.msg == "updateOK") {
		 
				window.location.href="${pageContext.request.contextPath}/manager/getRightActionList.html" 
				
			} else {
				layer.msg('修改失败');
			}
		},
		error : function() {
			alert("请求出错，可能连接不上服务器");
		}

	})
	
	
	
});



</script>



<script type="text/javascript">


	//初始化  下拉列表的 选中项

	  function initSelect() {
		// 根据 父id 设置 select的选中值  value里的！
		$("#parentId").val(${pages.conditions.firstMenuId});

		$("#typeid").val(${pages.conditions.secMenuId});
		
		FirstMenuChange();
		
	}

	initSelect();  

	 

	/* 删除     */
	function deleteById(id) {

		$.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath}/manager/deleteById.html',
					data : {
						"id" : id
					},
					dataType : 'json', //要求服务器返回 json对象 而不是字符串
					success : function(data) {

						if (data.msg == "deleteok") {
							//$("#tr_" + id).remove();
							layer.msg('删除成功');
							
							window.location.href="${pageContext.request.contextPath}/manager/getRightActionList.html" 
							
						} else {
							layer.msg('删除失败');
						}
					},
					error : function() {
						alert("请求出错，可能连接不上服务器");
					}

				})
	}

	/* 响应菜单联动 */
	function parentIdchanged() {
		var op = $("#parentId option:selected");
		var parentId = op.val();

		if (parentId == 0) {
			$("#typeid").html("<option value=0>全 部</option>");

		}

		else {
			$ .ajax({
						type : 'POST',
						url : '${pageContext.request.contextPath}/manager/getSecMenuByFartherId.html',
						data : {
							"parentId" : parentId
						},
						dataType : 'json', //要求服务器返回 json对象 而不是字符串
						success : function(arr) {

							//   var arr=eval(data);

							var childList;
							var length = arr.length;
							for (var i = 0; i < length; i++) {
								 
									childList = childList
											+ "<option value="+arr[i].id+">"
											+ arr[i].name + "</option>";
							}
							$("#typeid").html(childList);

						},
						error : function() {
							alert("出错");
						}

					})

		}
	}
		
		
		
		/* 响应新增的菜单联动 */
		function FirstMenuChange() {
			var op = $("#addFirstMenu option:selected");
			var addFirstMenu = op.val();

			if (addFirstMenu == 0) {
				$("#addSecMenu").html("<option value=0>请选择！</option>");

			}

			else {
				$.ajax({
							type : 'POST',
							url : '${pageContext.request.contextPath}/manager/getSecMenuByFartherId.html',
							data : {
								"parentId" : addFirstMenu
							},
							dataType : 'json', //要求服务器返回 json对象 而不是字符串
							success : function(arr) {

								//   var arr=eval(data);

								var addSecMenu;
								var length = arr.length;
								for (var i = 0; i < length; i++) {
									 
										addSecMenu = addSecMenu
												+ "<option value="+arr[i].id+">"
												+ arr[i].name + "</option>";
								}
								$("#addSecMenu").html(addSecMenu);

							},
							error : function() {
								alert("出错");
							}

						})

			}
	}
		
		
		//响应 新增 按钮
		
		function   addRightAction()
		{
			
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/manager/addRightActionAction.html',
				data : {
					  "rightId": $("#addSecMenu").val(),
					   "action": $("#action").val(),
				       "description":$("#description").val()
					 
					 
				},
				dataType : 'json', //要求服务器返回 json对象 而不是字符串
				success : function(data) {

					self.location="manager/getRightActionList.html"    
					    

				},
				error : function() {
					alert("出错");
				}

			})
 
		}
</script>

 
</html>