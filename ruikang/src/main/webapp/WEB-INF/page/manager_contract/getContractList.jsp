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
<title>合同列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<!--     <link  rel="stylesheet" href="components/jqueryui/jquery-ui.min.css"> -->

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
    <script src="components/laydate/laydate.js"></script>
<!--     <script src="components/jqueryui/jquery-ui.min.js"></script> -->
    
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">项目管理</a></li>
  <li class="active"> 合同列表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    method="post"  class="form-inline">  
 	
 	
 		                <input type="hidden"  id="htTime" name="htTime" value="${pages.conditions.beginHT  }">
						<input type="hidden"  id="lastTime" name="lastTime" value="${pages.conditions.endHT  }">
						
						
    
    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['contractId']" >合同编号:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.contractId }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.contractId }"    name="conditions['contractId']" >
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据合同编号模糊查询"   name="conditions['contractId']"   >
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	      <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['flag']" >合同状态:</label>
<!-- 		   未领取  1 已领取  2 已签订 3 还款完毕 4  续签5  异常6 -->
		  <select  id="flag"    name="conditions['flag']"  class="  form-control"  style="margin-left:10px;width:120px">
              
              <option value="0"  selected="selected">--全部--</option>
               <option value="1"  >未领取</option>
              <option value="2">已领取</option>
              <option value="3">已经签订</option>
              <option value="4">还款完毕</option>
               <option value="5">有续签</option>
               <option value="6"  >异常</option>
            </select>
 
       </div>
	
 	<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"     for="beginTime ">合同签署时间:</label>
          
                  <input type="text"  class="form-control"  id="beginTime"   name="conditions['beginHT']"   value="${pages.conditions.beginHT}"  >
              
	  </div> 
	
			<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="endTime ">合同签署时间结束:</label>
          
                  <input type="text"  class="form-control"  id="endTime"    name="conditions['endHT']"    value="${pages.conditions.endHT}"  >
              
	  </div>  
	  
	  
 
       
	  
	  	       <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['manager']" >理财经理:</label>
 
		  <select  id="manager"    name="conditions['manager']"  class="  form-control"  style="margin-left:10px;width:120px">
              
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
       
 
 
 	<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary" type="button"   id="btnsearch" onclick="search()">查询</button>
              
	</div> 
	
	
	
<!-- 		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="exportBtn">导出</label>
          
          <button class="btn btn-success" type="submit"   id="exportBtn" name="exportBtn"  onclick="download()">导出excel</button>
              
	</div>  -->
	
	
	
	
 
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
	
	        
				<th>合同编号</th>
				
				<th>融资客户</th>
				
				<th>融资项目</th>
				<!-- <th>融资金额</th> -->
				 	<th>理财经理</th>
				 		<th>理财客户</th>
				 		<th>合同签订时间</th>
				 			<th>合同期限</th>
				 			<th>合同金额</th>
				 <th>当前状态</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
  
				<c:forEach var="vo" items="${pages.pageDatas}">
				
					<tr id="tr_${vo.contract.id }">
					
					     
						<td><c:out value="${vo.contract.contractid}" /></td>
						
						<td><c:out value="${vo.rongzi.name}" /></td>
						
						<td><c:out value="${vo.invest.name}" /></td>
					 
						 <%--   <td> <fmt:formatNumber value="${vo.invest.rzmoney }" type="currency" pattern="#0"/>     </td> --%>
						     
						 	<td><c:out value="${vo.manager.realname}" /></td>
						 		<td><c:out value="${vo.vip.name}" /></td>
						 		
						 		
						 		
						 		 <td><fmt:formatDate value="${vo.contract.putcontracttime}" pattern="yyyy-MM-dd" /> </td>
						 		 
						 		<td><c:out value="${vo.contract.longtime}" /></td>
						 		    
						 		        <!--   第一个条件满足了 就跳出 choose -->
						 		        <c:choose>
						 		             
						 		               <c:when test="${ not empty vo.contract.memony}">
						 		                     <td> <fmt:formatNumber value="${vo.contract.memony }" type="currency" pattern="#0"/>     </td>
						 		               </c:when>
						 		               
						 		             <c:when test="${ not empty vo.contract.maybememony}">
						 		                     <td> 预计:<fmt:formatNumber value="${vo.contract.maybememony }" type="currency" pattern="#0"/>     </td>
						 		               </c:when>
						 		            
						 		               <c:otherwise>
						 		                      <td> -   </td>
						 		               </c:otherwise>
						 		               
						 		        </c:choose>
						 		     
						 		     <!--  未领取  1 已领取  2 已签订 3 还款完毕 4  续签5  异常6 -->
						 		         <c:choose>
					                 <c:when test="${vo.contract.flag==1 }">
					                        <td>未领取</td>
					                 </c:when>
					                   <c:when test="${vo.contract.flag==2 }">
					                        <td><fmt:formatDate value="${vo.contract.getcontracttime}" pattern="yyyy-MM-dd" />已领 </td> 
					                 </c:when>
					                   <c:when test="${vo.contract.flag==3 }">
					                        <td> 已签</td>
					                 </c:when>
					                   <c:when test="${vo.contract.flag==4 }">
					                        <td>还款完毕</td>
					                 </c:when>
					                   <c:when test="${vo.contract.flag==5 }">
					                        <td>合同有续签情况</td>
					                 </c:when>
					                     <c:when test="${vo.contract.flag==6 }">
					                        <td>异常</td>
					                 </c:when>
					                 
					                  <c:otherwise>
					                    <td>未领取</td>
					                  </c:otherwise>
					          
					          </c:choose>
				 
						<td>
						    
						      <c:choose>
						          <c:when test="${vo.contract.flag==1 }">
						             <a href="${pageContext.request.contextPath}/manager/receiveContractInit.html?id=${vo.contract.id}"> 领取 </a>  
						            <a href="javascript:void(0)" onclick="deleteById(${vo.contract.id })"> 删除 </a>
						          </c:when>
						          
						             <c:when test="${vo.contract.flag==2 }">
						           <!--   <span> 已领取</span>   -->
						              <a href="javascript:void(0)" onclick="deleteById(${vo.contract.id })"> 删除 </a>
						          </c:when>
						           
						            <c:otherwise>
						            
						               不可编辑
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
                   url="${pageContext.request.contextPath}/manager/getContractList.html?conditions['flag']=${pages.conditions.flag}&conditions['manager']=${pages.conditions.manager}&conditions['contractId']=${pages.conditions.contractId}&conditions['beginHT']=${pages.conditions.beginHT }&conditions['endHT']=${pages.conditions.endHT }"  />     
 
           </c:if>		 

	
  </div>
	 

</body>


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
        min: laydate.now(),
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
 
 
 //把 搜索条件 保留
 $("#flag").val(${pages.conditions.flag});
 
 
 function search()
 {
	 
	  
		   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/getContractList.html');
	       
		   $("#searchForm").submit();
 }
 
 
 
 
 function download(){
	 
	  
	   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/exportContractList.html');
	   $("#searchForm").submit();
	   
	   
	  
	 }
 
 
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
				}  else if (msg.msg=="hasBill")
					{
					layer.msg('对不起，财务单据中使用了该合同，不能删除此合同！');
					
					}
				  else if (msg.msg=="hasSchedule")
					{
					
						layer.msg('对不起，还款计划中使用了该合同，不能删除此合同!');
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
