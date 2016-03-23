<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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



	<div class="row" style="margin-top:20px;margin-left:20px">
	
	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getFinancialList.html" method="post">  
	<div class="col-lg-4">
	
	<div class="input-group">
	
          <c:choose>
			   <c:when test="${ not empty pages.params }">    
			             	<input type="text" class="form-control"  value="${pages.params }"   id="params" name="params" >
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据项目名称模糊查询"   id="params" name="params"   >
			   </c:otherwise>  
		</c:choose>
	

	
	<span class="input-group-btn">
	   <button class="btn btn-default" type="submit"   id="btnsearch">查询</button>
	</span>
	</div>
	</div>
	
	</form>
	
	</div>

<!-- {"currPage":1,"totalPages":1,"pageSize":10,"totalRecords":2,"isHavePrePage":false,"isHaveNextPage":false,
"pageDatas":[  {"id":24,"userid":1,"time":"Oct 24, 2015 11:52:54 AM","title":"","longtime":"","apr":"","backtype":"","readcount":0,"content":"啊暗室逢灯碍事大声道"}
                       {"id":27,"userid":1,"time":"Oct 24, 2015 11:56:23 AM","title":"啊啊","longtime":"都33","apr":"版本","backtype":"44","readcount":0,"content":"阿斯顿发"}]} -->

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:20px">
	<table class="table table-striped  table-bordered table-hover">
	<thead   >
	<tr  style="background-color:#dcdcdc; ">
	 
	<th>项目名称</th>
	<th>年化收益</th>
	<th>投资期限</th>
     <th>还款方式</th>
     <th>浏览次数</th>
	<th>操作</th>
	</tr>
	</thead>

	<tbody id="tbody">

                <c:forEach var="financial"  items="${pages.pageDatas}">
					<tr id="tr_${financial.id }">
						 

						<td><c:out value="${financial.title}" /></td>
						<td><c:out value="${financial.apr}" /></td>
							<td><c:out value="${financial.longtime}" /></td>
							<td><c:out value="${financial.backtype}" /></td>
							<td><c:out value="${financial.readcount}" /></td>
							
                         <td>
							<a href="manager/updateFinancialInit.html?id=${financial.id } ">编辑 </a>
				 
									<a href="javascript:void(0)"   onclick="deleteById(${financial.id})" >删除</a>
							</td>
					</tr>
				</c:forEach>


	</tbody>

	</table>

    <c:if test="${pages.totalPages>1 }" >
	<nav  style="float:right;margin-right:40px">
	
	<ul class="pagination">
	
	  <!--  上一页 
	    <c:if test="${pages.isHavePrePage }">
	    	<li>
				<a href="#" aria-label="Previous">  <span aria-hidden="true">&laquo;</span>   </a>
			</li>
	    </c:if>
		-->			
		    
		       
		                   <!-- 根据总页数，绘制 页码，有多少也就绘制多少个 -->
	             	<c:forEach var="i"   begin="1"    end="${pages.totalPages}"    step="1">
				                     
				                    <!--  把当前页的 页码高亮显示，由于jstl 没有 if else  所以只能用 choose when otherwise来处理 -->
				                <c:choose>
				                
									   <c:when test="${ i==pages.currPage }">    
									                <li class="active"><a href="${pageContext.request.contextPath}/manager/getFinancialList.html?currPage=${i}&pageSize=${pages.pageSize}&params=${pages.params}">${i }</a></li>
									   </c:when>   
									  
									   <c:otherwise>   
									                 <li><a href="${pageContext.request.contextPath}/manager/getFinancialList.html?currPage=${i}&pageSize=${pages.pageSize}&params=${pages.params}">${i }</a></li>
									   </c:otherwise>  
								</c:choose>

				   </c:forEach>
				   
                 
                   
                   
	         <!--  下一页 
		    <c:if test="${pages.isHaveNextPage }">
					<li>
	                <a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
	              </li>
	        </c:if>
              -->
	
	
			</ul>
			</nav>

       </c:if>
       
       
	</div>
 

	</body>
 

<script type="text/javascript">

 
 
/* 删除  */
 function deleteById(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteFinancialById.html',
			data : {"id" : id},
		 
			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
			   success : function(data) {
 
				if (data.msg== "deleteOK") {
					$("#tr_" + id).remove();
					layer.msg("删除成功");
					return;
				}  else
					{
					   layer.msg("删除失败");
					}
			},
			error : function() {
				alert("请求出错，可能连接不上服务器");
			}

		})
	}

 
  
	
</script>


 


</html>