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

	<form name="contentForm" method="post"   action="manager/getContentList.html">

		<div class="form-group form-inline" style="margin:20px">
			<strong style="font-size:1.2em"> 类 别:</strong>
			 <select  id="parentId" onchange="parentIdchanged( )" name="parentId" 	class="  form-control" style="margin-left:10px">
				<option value="0">-全 部-</option>

				<c:forEach var="item" items="${typeList}">

					<option value="${item.id}">${item.name}</option>

				</c:forEach>
			</select> 
			
			
			<select id="typeid" name="typeid" class=" form-control">
				<option value=0>-全 部-</option>
				<c:forEach var="childItem" items="${childList}">
					<option value="${childItem.id}">${childItem.name}</option>
				</c:forEach>

			</select> 
			
			<label for="filetitle" style="margin-left:10px"> 
			<strong style="font-size:1.2em"> 标 题： </strong>
				</label> 
				<input type="text"  class="form-control" id="filetitle"   name="filetitle" style="width:30%"  value="${pages.conditions.filetitle}"> 
				<input type="submit"  name="button" value="查 询" class="btn btn-primary" />
		</div>



	</form>

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:20px">
		<table class="table table-striped  table-bordered table-hover">
			<thead>
				<tr style="background-color:#dcdcdc; ">
					<th>编号</th>
					<th>标题</th>
					<th>所属类别</th>

					<th>发布时间</th>
					<th>浏览次数</th>
					<th>是否显示</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody id="tbody">

				<c:forEach var="item" items="${pages.pageDatas}">
					<tr id="tr_${item.content.id }">
						<td><c:out value="${item.content.id}" /></td>
						<td><c:out value="${item.content.filetitle}" /></td>

						<td>${item.fartherTypeInfo.name } --${item.typeInfo.name }</td>

						<td><fmt:formatDate value="${item.content.time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>

						<td><c:out value="${item.content.readcount}" /></td>

						<td><c:choose>
								<c:when test="${item.content.isshow}">
									<input type="checkbox" checked onclick="changFlag(this,${item.content.id})">显示
								  </c:when>

								<c:when test="${! item.content.isshow}">
									<input type="checkbox"  	onclick="changFlag(this,${item.content.id})">显示
								  </c:when>

								<%--  <c:otherwise>   
								    ${param.username} is employee.  
								  </c:otherwise>  --%>
							</c:choose></td>
						<td>
						
						<a href="manager/getContentInfo.html?itemId=${item.content.id }">详情</a>

							<a href="manager/updateContentInit.html?itemId=${item.content.id }">编辑 </a> 
						<a href="javascript:void(0)"  onclick="deletecontent(${item.content.id})">删除</a></td>

					</tr>
				</c:forEach>


			</tbody>

		</table>

		
		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
   <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" url="${pageContext.request.contextPath}/manager/getContentList.html?params=${pages.params}&parentId=${pages.conditions.type.parentid}&typeid=${pages.conditions.type.id}&filetitle=${pages.conditions.filetitle}"  />  
 
  
 <%--   <c:if test="${pages.totalPages>1 }"> 
	 		<nav style="float:right;margin-right:40px">
				<ul class="pagination">
					    <c:if test="${pages.isHavePrePage }">
					    	<li>
									<a href="${pageContext.request.contextPath}/content/contentlist.html?currPage=${pages.currPage-1}&pageSize=${pages.pageSize}&params=${pages.params}&filetitle=${pages.conditions.filetitle}&parentId=${pages.conditions.type.parentId}&typeid=${pages.conditions.type.id}" aria-label="Previous"> 
								 <span aria-hidden="true">上一页</span>   </a>
							</li>
					    </c:if>
	 
					<!-- 根据总页数，绘制 页码，有多少也就绘制多少个 -->
					<c:forEach var="i" begin="1" end="${pages.totalPages}" step="1">

						<!--  把当前页的 页码高亮显示，由于jstl 没有 if else  所以只能用 choose when otherwise来处理 -->
						<c:choose>

							<c:when test="${ i==pages.currPage }">
								<li class="active">
								<a href="${pageContext.request.contextPath}/content/contentlist.html?currPage=${i}&pageSize=${pages.pageSize}&params=${pages.params}&filetitle=${pages.conditions.filetitle}&parentId=${pages.conditions.type.parentId}&typeid=${pages.conditions.type.id}">${i }</a>
							</li>
							</c:when>

							<c:otherwise>
								<li>
								<a href="${pageContext.request.contextPath}/content/contentlist.html?currPage=${i}&pageSize=${pages.pageSize}&params=${pages.params}&filetitle=${pages.conditions.filetitle}&parentId=${pages.conditions.type.parentId}&typeid=${pages.conditions.type.id}">${i }</a>
								</li>
							</c:otherwise>
						</c:choose>

					</c:forEach>

				    <c:if test="${pages.isHaveNextPage }">
							<li>
			                	<a href="${pageContext.request.contextPath}/content/contentlist.html?currPage=${pages.currPage+1}&pageSize=${pages.pageSize}&params=${pages.params}&filetitle=${pages.conditions.filetitle}&parentId=${pages.conditions.type.parentId}&typeid=${pages.conditions.type.id}" aria-label="Next">
			                <span aria-hidden="true">下一页</span> 
			                </a>
			              </li>
			        </c:if>
				</ul>
			</nav>  

		  </c:if>  --%>


	</div>



</body>






<script type="text/javascript">
	//初始化  下拉列表的 选中项

	function initSelect() {
		// 根据 父id 设置 select的选中值  value里的！
		$("#parentId").val(${pages.conditions.type.parentid});

		$("#typeid").val(${pages.conditions.type.id});
	}

	initSelect();

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
			url : '${pageContext.request.contextPath}/manager/updateContentShowFlag.html',
			data : {
				"id" : id,
				"flag" : isUse
			},

			dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(data) {

				if (data.msg == "updateok") {

					layer.msg('更新成功');
				} else {
					layer.msg('未更新');
				}

			},
			error : function() {

				layer.msg("请求出错，可能连接不上服务器");
			}

		})

	}

	/* 删除     */
	function deletecontent(id) {

		$
				.ajax({
					type : 'POST',
					url : '${pageContext.request.contextPath}/manager/deleteContent.html',
					data : {
						"id" : id
					},
					dataType : 'json', //要求服务器返回 json对象 而不是字符串
					success : function(data) {

						if (data.msg == "deleteok") {
							$("#tr_" + id).remove();
							layer.msg('删除成功');
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
			$
					.ajax({
						type : 'POST',
						url : '${pageContext.request.contextPath}/manager/getContentTypesByParentId.html',
						data : {
							"parentId" : parentId
						},
						dataType : 'json', //要求服务器返回 json对象 而不是字符串
						success : function(arr) {

							//   var arr=eval(data);

							var childList;
							var length = arr.length;
							for (var i = 0; i < length; i++) {
								if (arr[i].id != 31)
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
</script>





</html>