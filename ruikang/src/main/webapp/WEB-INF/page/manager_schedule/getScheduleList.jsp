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
<title>还款计划表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

     

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="components/laydate/laydate.js"></script>
    
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">财务核算</a></li>
  <li class="active"> 还款计划表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    method="post"  class="form-inline">  
 	
 	
 		             <input type="hidden"  id="orderBy"  name="conditions['orderBy']"  value="${pages.conditions.orderBy }">
 		              <input type="hidden"  id="desc"  name="conditions['desc']"   value="${pages.conditions.desc }">
    
    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['name']" >合同编号:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.contractId }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.contractId }"    name="conditions['contractId']"   id="contractId"  maxlength="20" style="width:120px">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据合同编号模糊查询"   name="conditions['contractId']"    id="contractId"  maxlength="20" style="width:120px">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	
	    <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['flag']" >状态:</label>
 
<!--  还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常 -->
		  <select  id="flag"    name="conditions['flag']"  class="  form-control"  style="margin-left:10px;width:120px">
                  
                 <c:choose>
                 
                        <c:when test="${pages.conditions.flag==99 }">
                               <option value="99"  selected="selected" >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1">已还款</option>
			                      <option value="2">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.flag==0 }">
                               <option value="99"   >--全部--</option>
			                    <option value="0"  selected="selected">未到时间</option>
			                    <option value="1">已还款</option>
			                      <option value="2">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.flag==1}">
                               <option value="99"    >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1" selected="selected">已还款</option>
			                      <option value="2">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.flag==2}">
                               <option value="99"   >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1">已还款</option>
			                      <option value="2" selected="selected">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.flag==4 }">
                               <option value="99"   >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1">已还款</option>
			                      <option value="2">逾期</option>
							  <option value="4" selected="selected">异常</option>       
                        </c:when>
                      
                        <c:otherwise>
                               <option value="99"   selected="selected">--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1">已还款</option>
			                      <option value="2">逾期</option>
							  <option value="4" >异常</option>       
                        
                        </c:otherwise>
                     
                 </c:choose>
                  
                   
            </select>
 
       </div>
       
	
	    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['vip']" >理财客户:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.vip }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.vip }"    name="conditions['vip']"  id="vip"  maxlength="20">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="理财客户名称模糊查询"   name="conditions['vip']"  id="vip"  maxlength="20">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	
	   
	     
       
       
       
	  	       <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['manager']" >理财经理:</label>
 
		  <select  id="manager"    name="conditions['manager']"  class="  form-control"  style="margin-left:10px;width:120px">
              
                <option value=0>--全部--</option>
                 
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
       
	
 	<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"     for="beginTime ">还款时间:</label>
          
                  <input type="text"  class="form-control"  id="beginTime"   name="conditions['beginHT']"   value="${pages.conditions.beginHT}"  >
              
	  </div> 
	
			<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="endTime ">时间结束:</label>
          
                  <input type="text"  class="form-control"  id="endTime"    name="conditions['endHT']"    value="${pages.conditions.endHT}"  >
              
	  </div>  
	  
	  
 
       
	  
       
	
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary" type="button"   id="btnsearch" onclick="search()">查询</button>
              
	</div> 
	
	
	
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="exportBtn">导出</label>
          
          <button class="btn btn-success" type="submit"   id="exportBtn" name="exportBtn"  onclick="download()">导出excel</button>
              
	</div> 
	
	
 
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
	 
				<th>合同编号</th>
				
				<th >合同金额</th>
				<th id="contractTime">合同时间</th>
				<th>合同期限</th>
				<th>融资客户</th>
				<th>融资项目</th>
				<th>还款账户</th>
			<!-- 	<th>融资项目</th> -->
			 
				 	<th>理财经理</th>
				 		<th>理财客户</th>
				 		 <th>收款账号</th>
				 		<th>还款时间</th>
				 			<th>还款金额</th>
				 <th>当前状态</th>
				  <th>操作</th>
				</tr>
			</thead>

			<tbody>
  
				<c:forEach var="vo" items="${pages.pageDatas}">
				
					<tr id="tr_${vo.schedule.id }">
					
							 
							
						<td><c:out value="${vo.contract.contractid}" /></td>
						
						
						
						<td> <fmt:formatNumber value="${vo.bill.memony }" type="currency" pattern="#0.00"/> </td>
						
						  <td> <fmt:formatDate value="${vo.bill.contracttime}" pattern="yyyy-MM-dd" />     </td>
						
						<td><c:out value="${vo.bill.longtime}" />个月</td>
						
										<td><c:out value="${vo.rz.name}" /></td>
											<td> ${vo.invest.name} </td>
											<td> 
											        ${vo.rzbank.bankname}</br>
											        ${vo.rzbank.banknum }
											</td>
						<%-- <td><c:out value="${vo.invest.name}" /></td> --%>
					 
						 <%--   <td> <fmt:formatNumber value="${vo.invest.rzmoney }" type="currency" pattern="#0"/>     </td> --%>
						     
						 	<td><c:out value="${vo.manager.realname}" /></td>
						 		<td><c:out value="${vo.schedule.vipname}" /></td>
						 		
						 		 <td>
						 		    ${vo.vip.bankname} <br>
						 		     ${vo.vip.banknum}  
						 		 </td>
						 		    
						 		        <!--   第一个条件满足了 就跳出 choose -->
						 		        <c:choose>
						 		             
						 		               <c:when test="${ not empty vo.schedule.backtime}">
						 		                     <td> <fmt:formatDate value="${vo.schedule.backtime}" pattern="yyyy-MM-dd" />     </td>
						 		               </c:when>
						 		               
						 		             <c:when test="${ not empty vo.schedule.memonytime}">
						 		                     <td><fmt:formatDate value="${vo.schedule.memonytime}" pattern="yyyy-MM-dd" />    </td>
						 		               </c:when>
						 		      
						 		               
						 		        </c:choose>
						 		        
						 		        
						 		          <td> <fmt:formatNumber value="${vo.schedule.memony }" type="currency" pattern="#0.00"/>     </td>
						 		          
						 		          
						 		     
						 		     <!--  还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常 -->
						 		         <c:choose>
					                 <c:when test="${vo.schedule.flag==0}">
					                        <td>还没到还款时间</td>
					                 </c:when>
					                   <c:when test="${vo.schedule.flag==1}">
					                        <td> 已经还款</td> 
					                 </c:when>
					                   <c:when test="${vo.schedule.flag==2 }">
					                        <td>逾期</td>
					                 </c:when>
					                   <c:when test="${vo.schedule.flag==4 }">
					                        <td>异常</td>
					                 </c:when>
					                
					          
					          </c:choose>
				 
				        <td> 
				        
				            <c:choose>
				                 <c:when test="${vo.schedule.flag==1 }">
				                                    已还款 
				                 </c:when>
				                 <c:otherwise>
				                   <a href="${pageContext.request.contextPath}/manager/updateScheduleInit.html?id=${vo.schedule.id}">登记 </a>  
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
                   url="${pageContext.request.contextPath}/manager/getScheduleList.html?conditions['orderBy']=${pages.conditions.orderBy}&conditions['desc']=${pages.conditions.desc}&conditions['contractId']=${pages.conditions.contractId}&conditions['flag']=${pages.conditions.flag}&conditions['manager']=${pages.conditions.manager}&conditions['beginHT']=${pages.conditions.beginHT }&conditions['endHT']=${pages.conditions.endHT }&conditions['vip']=${pages.conditions.vip }"  />     
 
           </c:if>	  	 
           
       	
     
	
  </div>
	 

</body>

<!-- <script>

 //0 默认排序  1   降序
 var   desc=0;
 
 // 按合同时间排序
 $("#contractTime").click(function(){
	 
	 $("#orderBy").val("contractTime");
	 
     if (desc==0)
    	 {
    	         $("#orderBy").val("contractTime");
    	         $("desc").val("1");
    	         desc=1;
    	 }else
    		 {
    		 $("#orderBy").val("contractTime");
    		  $("desc").val("0");
	         desc=0;
    		 }
	 
	   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/getScheduleList.html');
	   $("#searchForm").submit();
 });



</script> -->
 

 <script>
 
 $("#flag").val(${pages.conditions.flag});
 
 function search()
 {
	 
	  
		   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/getScheduleList.html');
	   $("#searchForm").submit();
 }
 
 function download(){
	 
 
   //  var url='${pageContext.request.contextPath}/manager/exportScheduleList.html';
  //   window.open(url);
  
   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/exportScheduleList.html');
   $("#searchForm").submit();
   
   
  
 }
 
 
 
 </script>
 
 
 <script>
    var start = {
        elem: '#beginTime',
        format: 'YYYY-MM-DD',
        // min: laydate.now(), //设定最小日期为当前日期
        max: '2019-06-16 23:59:59', //最大日期
        istime: false,
        istoday: false,
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
          // end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#endTime',
        format: 'YYYY-MM-DD',
      //  min: laydate.now(),
        max: '2019-06-16 23:59:59',
        istime: false,
        istoday: false,
        choose: function(datas){
           start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>

 
<script>
	/*    通过ajax 提交删除请求 ，根据返回结果 删除本地 表格里的数据，实现无刷新删除 */

	function deleteById(id) {

		$.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteContractById.html',
			data : {"id" : id},
		 
		       dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(msg) {

				if (msg.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				} else {
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
