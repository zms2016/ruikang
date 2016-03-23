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
<title>待收款列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

     

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="components/laydate/laydate.js"></script>
    
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">项目管理</a></li>
  <li class="active"> 待收款列表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    method="post"  class="form-inline">  
 	
 	
 		           <%--   <input type="hidden"  id="orderBy"  name="conditions['orderBy']"  value="${pages.conditions.orderBy }">
 		              <input type="hidden"  id="desc"  name="conditions['desc']"   value="${pages.conditions.desc }"> --%>
    
    	 
    	 	    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['vip']" >借款人:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.rzname }">    
			             	<input type="text" class="form-control  "  value="${pages.conditions.rzname }"    name="conditions['rzname']"  id="rzname"  maxlength="20"   style="width:120px">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control   " placeholder="借款人姓名"   name="conditions['rzname']"  id="rzname"  maxlength="20"  style="width:120px">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	
		 	    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['investname']" >借款项目:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.investname }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.investname }"    name="conditions['investname']"  id="investname"  maxlength="20" style="width:140px">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="借款项目"   name="conditions['investname']"  id="investname"  maxlength="20"   style="width:140px">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	
	
	    <div class="form-group form-inline">
		  <label style="font-size:1em;margin-left:15px"   for="conditions['backflag']" >状态:</label>
 
<!--  '还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常',-->
		  <select  id="flag"    name="conditions['backflag']"  class="  form-control"  style="margin-left:10px;width:120px">
                  
                 <c:choose>
                 
                        <c:when test="${pages.conditions.backflag==99 }">
                               <option value="99"  selected="selected" >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1">已收款</option>
			                      <option value="2">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.backflag==0 }">
                               <option value="99"   >--全部--</option>
			                    <option value="0"  selected="selected">未到时间</option>
			                    <option value="1">已收款</option>
			                      <option value="2">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.backflag==1}">
                               <option value="99"    >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1" selected="selected">已收款</option>
			                      <option value="2">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.backflag==2}">
                               <option value="99"   >--全部--</option>
			                    <option value="0" >未到时间</option>
			                    <option value="1">已收款</option>
			                      <option value="2" selected="selected">逾期</option>
							  <option value="4">异常</option>       
                        </c:when>
                                 <c:when test="${pages.conditions.backflag==4 }">
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
	
	
<!-- 	
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="exportBtn">导出</label>
          
          <button class="btn btn-success" type="submit"   id="exportBtn" name="exportBtn"  onclick="download()">导出excel</button>
              
	</div>  -->
	
	
 
 </form>
</div> 
 
 
	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
	 
				<th>序号</th>
				<th >项目名称</th>
				<th>项目编号</th>
				<th>融资客户</th>
				<th  >合同时间</th>
				<th>融资金额</th>
				<th>还款类型</th>
				<th>本期应收</th>
				<th>收款时间</th>
				<th>还款期数  </th>
				<th>还款账户</th>
			    <th>当前状态</th>
				 <th>实际收款时间</th>
				  <th>操作</th>
				</tr>
			</thead>
  
	<!-- 		"backmoney":280.0,
			"backTime":"Mar 25, 2016 12:00:00 AM",
			"longtime":4,
			"backsort":1,
			"lastflag":false,
			"backflag":0,
			"bankname":"招商银行洪都支行",
			"banknum":"6214837902151517",
			"contracttime":"Mar 1, 2016 12:00:00 AM" -->
			<tbody>
 
				<c:forEach var="vo" items="${pages.pageDatas}" varStatus="status">
					<tr id="tr_${vo.id }">
						<td> ${status.count}</td> <%-- <td> ${status.index}</td> 从0开始--%>
                           <td > ${vo.investname}</td>
                            <td > ${vo.id}</td>
                              <td  > ${vo.rzname}</td>
                                 <td> <fmt:formatDate value="${vo.contracttime}" pattern="yyyy-MM-dd" />     </td>
					    	<td> <fmt:formatNumber value="${vo.rzmoney }" type="currency" pattern="#0"/> </td>
						 	<td>
						 	       <c:choose>
						 	          <c:when test="${vo.backtype==0 }">
						 	             先息后本
						 	           </c:when>
						 	             <c:when test="${vo.backtype==1 }">
						 	             等额本金
						 	           </c:when>
						 	             <c:when test="${vo.backtype==2 }">
						 	              一次性还本付息
						 	           </c:when>
						 	       </c:choose>
						 	</td>
						 	   <td> <fmt:formatNumber value="${vo.backmoney }" type="currency" pattern="#0.00"/>     </td>
						 	    <td> <fmt:formatDate value="${vo.backTime}" pattern="yyyy-MM-dd" />     </td>
						 	    <td>第 ${vo.backsort}/${vo.longtime }期 </td>
						 	    <td>${vo.bankname } <br> ${vo.banknum }</td>
						 		     <!--  还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常 -->
						 		         <c:choose>
					                 <c:when test="${vo.backflag==0}">
					                        <td>未到收款时间</td>
					                 </c:when>
					                   <c:when test="${vo.backflag==1}">
					                        <td> 已经收款</td> 
					                 </c:when>
					                   <c:when test="${vo.backflag==2 }">
					                        <td>逾期</td>
					                 </c:when>
					                   <c:when test="${vo.backflag==4 }">
					                        <td>异常</td>
					                 </c:when>
					                </c:choose>
				              
				               
				                <td>
				          
				               <c:choose>
				                        <c:when test="${vo.realtime !=null }"><fmt:formatDate value="${vo.realtime}" pattern="yyyy-MM-dd" /></c:when>
				                        <c:otherwise>--</c:otherwise>
				               </c:choose>
				               </td>
				               
				               <td> 
				            <c:choose>
				                 <c:when test="${vo.backflag==1 }">      已收款 
				                 </c:when>
				                 <c:otherwise>
				                   <a href="${pageContext.request.contextPath}/manager/updateReceiveInit.html?id=${vo.id}">登记 </a>  
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
                   url="${pageContext.request.contextPath}/manager/getReceiveList.html?conditions['investname']=${pages.conditions.investname}&conditions['rzname']=${pages.conditions.rzname}&conditions['backflag']=${pages.conditions.backflag}&conditions['beginHT']=${pages.conditions.beginHT }&conditions['endHT']=${pages.conditions.endHT }&conditions['vip']=${pages.conditions.vip }"  />     
 
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
	 
	  
		   $("#searchForm").attr("action", '${pageContext.request.contextPath}/manager/getReceiveList.html');
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
