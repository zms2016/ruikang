<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="page" uri="http://zms.20032015.com/pager"%>


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
<title>理财单据详情</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>


</head>

<body style="overflow-x: hidden; overflow-y: auto;">

	<ol class="breadcrumb">
		<li><span class="glyphicon glyphicon-home"></span><a
			href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
		<li><a href="javascript:void(0)">财务核算</a></li>
		<li class="active">理财单据</li>
	</ol>

	<div class="container" style="margin-bottom:100px">


		<div class="panel panel-default"  style="margin-bottom:20px">
			<div class="panel-heading">
				<%-- 客户名称: ${visitVo.vipName }    &nbsp; &nbsp; <fmt:formatDate value="${visitVo.visitTime }" pattern="yyyy年MM月dd日" /> --%>
				理财单据详情
			</div>
			<div class="panel-body" style="margin-bottom:10px">

				<div style="border: 1px solid  #eeeeee;  border-radius:4px">

					<ol>
						<li>单据编号:${vo.bill.id }</li>
						<li>合同编号:${vo.contract.contractid }</li>
						<li>合同类型: <!--     还款方式 0 先息后本 1 等额本息 2 一次性还款付息 --> <c:choose>
								<c:when test="${vo.bill.backtype==0 }"> 先息后本</c:when>
								<c:when test="${vo.bill.backtype==1 }"> 等额本息</c:when>
								<c:when test="${vo.bill.backtype==2 }"> 一次性还本付息</c:when>
							</c:choose>

						</li>
						<li>合同金额:${vo.bill.memony }  </li>
                  <li>合同时间:<fmt:formatDate
								value="${vo.bill.contracttime}" pattern="yyyy年MM月dd日" />  </li>
                  <li>投资期限: ${vo.bill.longtime }  </li>
 
             
				</oll>

						</div>      
            
            
            
                  <div
					style="border: 1px solid  #eeeeee;  border-radius:4px;margin-top:20px">
                       <ol>
		                 <li>理财客户:${vo.vip.name }  </li>
		                  <li>开户银行:${vo.vip.bankname }  </li>
		                   <li>银行账号:${vo.vip.banknum }  </li>
		                  <li>理财经理: ${vo.manager.realname }  </li>
		               
                      </oll>
             
            
				</div>  
            
            
                  <div
					style="border: 1px solid  #eeeeee;  border-radius:4px;margin-top:20px">
                       <ol>
		                 <li>融资客户:${vo.rongzi.name }  </li>
		                  <li>融资项目:${vo.invest.name }  </li>
		                  <li>还款类型: <!--     还款方式 0 先息后本 1 等额本息 2 一次性还款付息 --> <c:choose>
								<c:when test="${vo.invest.backtype==0 }"> 先息后本</c:when>
								<c:when test="${vo.invest.backtype==1 }"> 等额本息</c:when>
								<c:when test="${vo.invest.backtype==2 }"> 一次性还本付息</c:when>
							</c:choose>

						</li>
		                   <li>银行账号:${vo.rzbank.bankname }  </li>
		                  <li>还款账号: ${vo.rzbank.banknum }  </li>
		               
                      </oll>
             
                 
				</div>  
                 
                 
                  
                               <table  class="table table-striped  table-bordered table-hover"  style="margin-bottom:10px;margin-top:20px">
		                        	<thead>
											<tr>
											<th>计划编号</th>
														<th>还款时间</th>
														<th>还款金额</th>
														<th>还款状态</th>
													 
														</tr>
													</thead>
										
													<tbody>
													 
												    <c:forEach var="item" items="${vo.schedules}">
				
				
				                                           	<tr id="tr_${item.id }">  
				                                           	                  <td> ${item.id }</td>   
				                                           	                   <td>   <fmt:formatDate  value="${item.memonytime }" pattern="yyyy年MM月dd日" /> </td>
				                                           	                   <td>  ${item.memony }  </td>
				                                           	                        <td>  
				                                           	                                    <!--   还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常 -->
				                                           	                       <c:choose>
													                                      <c:when test="${item.flag==0 }"> 还未到还款时间</c:when>
													                                      <c:when test="${item.flag==1}"> 已经还款</c:when>
													                                       <c:when test="${item.flag==2 }"> 逾期</c:when>
													                                       <c:when test="${item.flag==4 }"> 异常</c:when>
													                              </c:choose>
				                                           	                        
				                                           	                 </td>
				                                           	                                
				                                           	</tr>
				                                           	
				                                           	</c:forEach>  
													
													</tbody>
                                                </table>
                                                
                         
                 
                 
                      
  
          
  </div>
</div>

</div>


</html>
