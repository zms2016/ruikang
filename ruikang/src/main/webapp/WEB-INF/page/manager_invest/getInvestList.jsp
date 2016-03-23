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
<title>融资项目列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
<!--   <link  rel="stylesheet" href="components/jqueryui/jquery-ui.min.css"> -->

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 <script src="js/zmsTools.js"></script>
  <!--   <script src="components/jqueryui/jquery-ui.min.js"></script> -->
    <script src="components/laydate/laydate.js"></script>
 <script src="components/layer2/layer/layer.js"></script>
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
 <li><a href="javascript:void(0)">项目管理</a></li>
  <li><a href="javascript:void(0)">融资项目</a></li>
  <li class="active"> 融资项目列表</li>
</ol>


 <div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getInvestList.html" method="post" class="form-inline">  
    
    
    	<input type="hidden"  id="htTime" name="htTime" value="${pages.conditions.beginHT }">
	    <input type="hidden"  id="lastTime" name="lastTime" value="${pages.conditions.endHT }">
						
						
    <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="conditions['name']" >项目名称:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.name }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.name }"   id="name"   name="conditions['name']"  maxlength="20">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control" placeholder="根据项目名称模糊查询"   id="name" name="conditions['name']"   maxlength="20">
			   </c:otherwise>  
		</c:choose>
 
               
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
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary" type="button"   id="btnsearch">查询</button>
              
	</div> 
	
	
 
 	<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="addBtn ">新增按钮:</label>
          
         <a href="manager/addInvestInit.html"  class="btn btn-success"  style="margin-left:120px; "  id="addBtn">增加融资项目</a>
              
	</div> 
 
        
        
 </form>
</div> 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				<th>项目名称</th>
				<th>项目编号</th>
				<th>融资客户</th>
				<th>融资金额</th>
				<th>剩余金额</th>
				<th>还款方式</th>
					<th>合同签署时间</th>
						<th>还款状态</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="vo" items="${pages.pageDatas}">
					<tr id="tr_${vo.invest.id }">
						<td><c:out value="${vo.invest.name}" /></td>
					 <td><c:out value="${vo.invest.investsid}" /></td>
					  <td><c:out value="${vo.rzName}" /></td>
					   <td> <fmt:formatNumber value="${vo.invest.rzmoney }" type="currency" pattern="#0"/>     </td>
					    <td> <fmt:formatNumber value="${vo.invest.needmoney }" type="currency" pattern="#0"/>     </td>
					          <c:choose>
					                 <c:when test="${vo.invest.backtype==0 }">
					                        <td>先息后本</td>
					                 </c:when>
					                   <c:when test="${vo.invest.backtype==1 }">
					                        <td>等额本息</td>
					                 </c:when>
					                   <c:when test="${vo.invest.backtype==2 }">
					                        <td>一次性还本付息</td>
					                 </c:when>
					                  <c:otherwise>
					                    <td>先息后本</td>
					                  </c:otherwise>
					          
					          </c:choose>
						 	<td>  <fmt:formatDate value="${vo.invest.contracttime}"  pattern="yyyy年MM月dd日" /> </td>  
						 		<td>  
						 		<!--   `flag` int(1) DEFAULT '0' COMMENT '融资产品标志  默认0，未满标  1 ，满标  2 ，还款中 3 ，还款完毕 4，异常 5，', -->
						 		  
						 		     <c:choose>
						 		     
						 		          <c:when test="${vo.invest.flag==0 }">
						 		             新录
						 		        </c:when>
						 		        <c:when test="${vo.invest.flag==1 }">
						 		           未满标
						 		        </c:when>
						 		        	        <c:when test="${vo.invest.flag==2 }">
						 		         满标
						 		        </c:when>
						 		        	        <c:when test="${vo.invest.flag==3}">
						 		       还款中
						 		        </c:when>
						 		        	        <c:when test="${vo.invest.flag==4}">
						 		         还款完毕
						 		        </c:when>
						 		        	        <c:when test="${vo.invest.flag==5 }">
						 		          异常
						 		        </c:when>
						 		     
						 		     </c:choose>
						 		     
						 		</td>  
						<td>
						  <%--   <a href="manager/getVipInfo.html?id=${vo.vip.id }"> 详情 </a> --%>
						    <a href="${pageContext.request.contextPath}/manager/updateInvestInit.html?id=${vo.invest.id  }"> 修改 </a>  
						    <c:if test="${vo.invest.flag==0 }">
						    <a href="javascript:void(0)" onclick="deleteById(${vo.invest.id })"> 删除 </a>
						    </c:if>
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getInvestList.html?conditions['name']=${pages.conditions.name}&conditions['beginHT']=${pages.conditions.beginHT }&conditions['endHT']=${pages.conditions.endHT }"  />    
 
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
	  
	  
	  if ( $("#beginTime").val()!="")
		  {
		        if ( ! isDate($("#beginTime").val()))
		        	{
		        	layer.msg('开始时间格式不对');
		        	$("#beginTime").val("");
		        	return false;
		        	}
		  }
	  
	  if ( $("#endTime").val()!="")
	  {
	        if ( ! isDate($("#endTime").val()))
	        	{
	        	layer.msg('结束时间格式不对');
	        	$("#endTime").val("");
	        	return false;
	        	}
	  }
  
	  $("#searchForm").submit();
	
});

	/*    通过ajax 提交删除请求 ，根据返回结果 删除本地 表格里的数据，实现无刷新删除 */

	function deleteById(id) {
		   layer.confirm('确定要删除吗？', {icon: 0, title:'删除融资项目'}, function(index){
			   //开始访问服务器
			   
	  $.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/deleteInvestById.html',
			data : {"id" : id},
		 
		       dataType : 'json', //要求服务器返回 json对象 而不是字符串
			success : function(msg) {

				if (msg.msg == "deleteOK") {
					$("#tr_" + id).remove();
				 
					layer.msg('删除成功');
				} 
				 else  if(msg.msg="hasChange"){
						layer.msg('不能删除项目，因为还款状态已经变化了，请核查还款计划表是否还有数据！');
					}
				
				else  if(msg.msg="hasContract"){
					layer.msg('不能删除项目，因为有合同使用了它！');
				} else{
					layer.msg('删除失败');
				}
			},
			error : function() {
				alert("出错");
			}
	    })
 
	   //访问服务器结束
      layer.close(index);
    });


	}
</script>


 

<!-- <script>


$(function() {

    $( "#beginTime" ).datepicker();
    $( "#beginTime" ).datepicker( "option", "dateFormat", "yy-mm-dd");
    
    $( "#endTime" ).datepicker();
    $( "#endTime" ).datepicker( "option", "dateFormat", "yy-mm-dd");
    
    
    $( "#beginTime" ).datepicker().datepicker('setDate',$("#htTime").val());
     $( "#endTime" ).datepicker().datepicker('setDate',$("#lastTime").val());  
   
   
   
    
    
});


</script> -->

 
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



</html>
