<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="page" uri="http://zms.20032015.com/pager" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<title>理财收益</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

     

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="components/laydate/laydate.js"></script>
    
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active"> 理财收益</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"      method="post"  class="form-inline">  
 	
 	               <%--     <input type="hidden"  id="manager" name="conditions['manager']"   value="${managerId }"> --%>
 	
 		                <input type="hidden"  id="htTime" name="htTime" value="${pages.conditions.beginHT  }">
						<input type="hidden"  id="lastTime" name="lastTime" value="${pages.conditions.endHT  }">
						
						
    
    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['name']" >合同编号:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.contractId }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.contractId }"    name="conditions['contractId']"  maxlength="20" style="width:120px">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control"    name="conditions['contractId']"   maxlength="20" style="width:120px">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	
<!-- 	    <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['flag']" >状态:</label>
 
 还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常
		  <select  id="flag"    name="conditions['flag']"  class="  form-control"  style="margin-left:10px;width:120px">
              
                <option value="99">--全部--</option>
                  <option value="0">未到时间</option>
                    <option value="1">已还款</option>
                      <option value="2">逾期</option>
							  <option value="4">异常</option>               
            </select>
 
       </div> -->
       
	
	    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['vip']" >理财客户:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.vip }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.vip }"    name="conditions['vip']"   maxlength="20" style="width:120px">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control"     name="conditions['vip']"   maxlength="20" style="width:120px">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
 
                   <c:choose>
                        <c:when test="${fn:length(managers)>1}">
                     
                           <div class="form-group form-inline">
									  <label style="font-size:1em;margin-left:15px"   for="manager" >理财经理:</label>
								        
									  <select   id="manager"  name="conditions['manager']"  class="  form-control"  style="margin-left:10px;width:120px"  >
							              
							                <option value="0">--全部--</option>
							                 
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
                        
                        </c:when>
                     
                         <c:otherwise>
                      
                                  <div class="form-group form-inline">
									  <label style="font-size:1em;margin-left:15px"   for="dismanager" >理财经理:</label>
								        
									  <select    name="conditions['manager']"  class="  form-control"  style="margin-left:10px;width:120px"  >
							               
							                   <option value="${managers[0].id}"> ${managers[0].realname } </option>  
							                       
														               
							            </select>
							 
							       </div>
                        
                        </c:otherwise>  
                   
                   </c:choose>
                   
                  
	            
						  	    
	       
       
       
       
       
	
 	<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"     for="beginTime ">还款时间:</label>
          
                  <input type="text"  class="form-control"  id="beginTime"   name="conditions['beginHT']"   value="${pages.conditions.beginHT}"   style="width:120px">
              
	  </div> 
	
			<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="endTime ">时间结束:</label>
          
                  <input type="text"  class="form-control"  id="endTime"    name="conditions['endHT']"    value="${pages.conditions.endHT}"   style="width:120px">
              
	  </div>  
	  
	  
 
       
	  
       
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary" type="button"   id="btnsearch" onclick="search()">查询</button>
              
	</div> 
	
	
	 
	  <c:if test="${username=='张兰兰' or username=='管理员'  }">
	  
	  		<div class="form-group form-inline">
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="exportBtn">导出</label>
          <button class="btn btn-success" type="submit"   id="exportBtn" name="exportBtn"  onclick="download()">导出excel</button>
	</div> 
	  
	  </c:if>

	
	
 
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
	 
				<th>合同编号</th>
				<th>融资项目</th>
				 
				 	<th>理财经理</th>
				 		<th>理财客户</th>
				 		 		<th>合同时间</th>
				 		 			<th>合同期限</th>
				 	 
				 		<th>合同金额</th>
				 		<th>付息时间</th>
				 			<th>付息金额</th>
				 <th>当前状态</th>
			 
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
						 		
						 		      <td> <fmt:formatDate value="${vo.bill.contracttime}" pattern="yyyy-MM-dd" />     </td>   
						 		      <td> ${vo.bill.longtime}个月  </td>
						 			<td>  <fmt:formatNumber value="${vo.bill.memony }" type="currency" pattern="#0.00"/>   </td>
						 		 
						 		    
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
				 
				    
				
				  
		 
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		     <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getSchedules.html?conditions['manager']=${pages.conditions.manager}&conditions['contractId']=${pages.conditions.contractId}&conditions['beginHT']=${pages.conditions.beginHT }&conditions['endHT']=${pages.conditions.endHT }&conditions['vip']=${pages.conditions.vip }"  />     
 
           </c:if>		 

  
	
  </div>
	 

</body>

 <script>
 
 $("#flag").val(${pages.conditions.flag});
 
 
 function search()
 {
	 
	  
		   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/getSchedules.html');
	       
		   $("#searchForm").submit();
 }
 
   function download(){
 
  
   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/exportVipScheduleList.html');
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
       // min: laydate.now(),
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
