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
<title>融资客户 银行账户列表</title>

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
  <li class="active"> 银行账户列表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getRzBankList.html" method="post">  
    
    	<div class="col-sm-4">
	
	<div class="input-group">
	
          <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"    id="name"  name="conditions['name']"  maxlength="20">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据账号名称模糊查询"   id="name" name="conditions['name']"   maxlength="20" >
			   </c:otherwise>  
		</c:choose>
	

	
	<span class="input-group-btn">
	   <button class="btn btn-primary" type="button"   id="btnsearch">查询</button>
	</span>
	
	</div>
	
	</div>
 
 
 	 <div class="col-sm-2">
        
        	<a href="manager/addRzBankInit.html"  class="btn btn-success"  style="margin-left:120px; " >增加银行账户</a>
        </div>
        
        
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				<th>用户编号</th>
				<th>账号名称</th>
				<th>融资客户</th>
				 
				<th>开 户 行</th>
				<th>银行账号</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
 
<!-- {"currPage":1,"totalPages":1,"params":"","conditions":{},"pageSize":10,"totalRecords":1,"isHavePrePage":false,"isHaveNextPage":false,
"pageDatas":[{"bank":{"id":1,"name":"周扒皮的招行","bankname":"招行","banknum":"777777","rongziid":2,"memo":"南昌的招行"},
                     "rz":{"id":2,"name":"周扒皮2","mobilenum":"1872222223","address":"南昌威尼斯","card":"3622043322","memo":"穷鬼","flag":0}}
                    ]}	 -->		 
				<c:forEach var="vo" items="${pages.pageDatas}">
				
					<tr id="tr_${vo.bank.id }">
						<td><c:out value="${vo.bank.id}" /></td>
						<td><c:out value="${vo.bank.name}" /></td>
						<td><c:out value="${vo.rz.name}" /></td>
							<td> ${vo.bank.bankname }	</td>
							<td> ${vo.bank.banknum }</td>		 
						<%-- 	<td>  <fmt:formatDate value="${user.birthday}" pattern="yyyy年MM月dd日" /> </td> --%>
						<td>
						  <%--   <a href="manager/getVipInfo.html?id=${vo.vip.id }"> 详情 </a> --%>
						    <a href="${pageContext.request.contextPath}/manager/updateRzBankInit.html?id=${vo.bank.id  }"> 修改 </a>  
						    
							<a href="javascript:void(0)" onclick="deleteById(${vo.bank.id })"> 删除 </a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getRzBankList.html?conditions['name']=${pages.conditions.name}"  />    
 
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
			url : '${pageContext.request.contextPath}/manager/deleteRzBankById.html',
			data : {"id" : id},
		 
		       dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(msg) {

				if (msg.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				} else if (msg.msg="hasContracts") {
					layer.msg('该账号已经有合同关联！不能直接删除！');
				}
				else{
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
