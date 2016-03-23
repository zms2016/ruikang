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
<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>




</head>
<body>

    <c:choose>
    
       <c:when test="${loginname=='zll'  or loginname=='dw' or 'admin'  or  'txf'}">
                <div class="container    " style="margin-top:40px">


		<c:choose>
			<c:when test="${pages.totalRecords>0 }">

				<!--  开始表格 -->

				<div class="panel panel-default">
					<div class="panel-heading">今天 需要付息的理财客户信息</div>
					<div class="panel-body">


						<table class="table   table-hover">
							<thead>
								<tr>

									<th>合同编号</th>
									<th>融资项目</th>

									<th>理财经理</th>
									<th>理财客户</th>
									<th>合同时间</th>
									<th>合同期限</th>

									<th>合同金额</th>
									<th>付息时间</th>
									<th>付息金额</th>
									<th>操作</th>

								</tr>
							</thead>

							<tbody>

								<c:forEach var="vo" items="${pages.pageDatas}">

									<tr id="tr_${vo.schedule.id }">



										<td><c:out value="${vo.contract.contractid}" /></td>
										<td><c:out value="${vo.invest.name}" /></td>

										<%--   <td> <fmt:formatNumber value="${vo.invest.rzmoney }" type="currency" pattern="#0"/>     </td> --%>

										<td><c:out value="${vo.manager.realname}" /></td>
										<td><c:out value="${vo.schedule.vipname}" /></td>

										<td><fmt:formatDate value="${vo.bill.contracttime}"
												pattern="yyyy-MM-dd" /></td>
										<td>${vo.bill.longtime}个月</td>
										<td><fmt:formatNumber value="${vo.bill.memony }"
												type="currency" pattern="#0.00" /></td>


										<!--   第一个条件满足了 就跳出 choose -->
										<c:choose>

											<c:when test="${ not empty vo.schedule.backtime}">
												<td><fmt:formatDate value="${vo.schedule.backtime}"
														pattern="yyyy-MM-dd" /></td>
											</c:when>

											<c:when test="${ not empty vo.schedule.memonytime}">
												<td><fmt:formatDate value="${vo.schedule.memonytime}"
														pattern="yyyy-MM-dd" /></td>
											</c:when>


										</c:choose>


										<td><fmt:formatNumber value="${vo.schedule.memony }"
												type="currency" pattern="#0.00" /></td>

										<td><a
											href="${pageContext.request.contextPath}/manager/updateScheduleInit.html?id=${vo.schedule.id}">登记
										</a></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<!--   结束表格 -->

			</c:when>

			<c:otherwise>
				<div class="alert alert-success" role="alert">今天没有需要付息的客户</div>
			</c:otherwise>

		</c:choose>



	</div>
	
	
       </c:when>
       
       <c:otherwise>
                      
                	<div class="container alert alert-success" role="alert" style="margin-top:100px">欢迎使用！</div>
       
       </c:otherwise>
    
    
    </c:choose>


	





</body>









</html>