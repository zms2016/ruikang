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
<title>操作日志列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<script src="js/zmsTools.js"></script>

 <script src="components/layer2/layer/layer.js"></script>
 
 <script src="components/laydate/laydate.js"></script>
 
 <style>
 
 

.autocut {  
    
    overflow:hidden;  
    white-space:nowrap;  
    text-overflow:ellipsis;  
    -o-text-overflow:ellipsis;  
    -icab-text-overflow: ellipsis;  
    -khtml-text-overflow: ellipsis;  
    -moz-text-overflow: ellipsis;  
    -webkit-text-overflow: ellipsis;  
}  


 </style>
 
</head>
 
<body>
 
        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">账号和角色</a></li>
  <li class="active">操作日志列表</li>
</ol>

<div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getLogList.html" method="post"  class="form-inline">  
 
	 
                     
                           <div class="form-group form-inline">
									  <label style="font-size:1em;margin-left:15px"   for="userId" >操作员:  </label>
								        
									  <select   id="userId"  name="conditions['userId']"  class="  form-control"  style="margin-left:10px;width:120px"  >
									  
									     <option value="0">--全部--</option>
							                 
							                      <c:forEach var="user" items="${users}">
							                             <c:choose>
							                                   <c:when test="${user.id==pages.conditions.userId }">
							                                      <option value="${user.id}" selected="selected">${user.realname}</option>
							                                   </c:when>
							                                   <c:otherwise>
							                                      <option value="${user.id}">${user.realname}</option>
							                                   </c:otherwise>
							                             </c:choose>
							                   
							                       </c:forEach>     
							            
														               
							            </select>
							 
							       </div>
                        
               
	  
 
						
						
	
 
				       <div class="form-group form-inline">
					
					     <label style="font-size:1em;margin-left:15px"     for="beginTime ">操作时间:</label>
				          
				                  <input type="text"  class="form-control"  id="beginTime"   name="conditions['beginTime']"   value="${pages.conditions.beginTime}"  >
				              
					  </div> 
				 
							<div class="form-group form-inline">
					
					     <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="endTime ">时间结束:</label>
				          
				                  <input type="text"  class="form-control"  id="endTime"    name="conditions['endTime']"    value="${pages.conditions.endTime}"  >
				              
					  </div>  
	  
	  
	  	      
	  	        	   <div class="form-group form-inline">
	
						  <label style="font-size:1em;margin-left:15px"    for="content" >操作内容:</label>
					                 <c:choose>
								   <c:when test="${ not empty pages.conditions.content }">    
								             	<input type="text" class="form-control"  value="${pages.conditions.content }"    id="content" name="conditions['content']"   maxlength="20">
								   </c:when>   
								  
								   <c:otherwise>   
								               	<input type="text" class="form-control" placeholder="操作内容模糊查询"   id="content"  name="conditions['content']"   maxlength="20">
								   </c:otherwise>  
							</c:choose>
 
               
	                    </div> 
 
 
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary"    type="button"   id="btnsearch">查询</button>
  
	</div> 
	
	
 
 </form>
 
 
</div> 


 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				
		 
				<th>操作时间</th>
				<th>操作员</th>
				<th>操作内容</th>
				
				<th>来源ip</th>
		    
				</tr>
			</thead>

			<tbody>
 
			 
				<c:forEach var="item" items="${pages.pageDatas}">
			 
					<tr  >
						<td> ${item.modiTime}</td>
						
						<td>  ${item.userName }  </td>
						
						
						 <td> <div > ${item.content }</div></td>
						 
			             <td> ${item.sourceIp }  </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getLogList.html?conditions['userId']=${pages.conditions.userId }&conditions['content']=${pages.conditions.content}&conditions['beginTime']=${pages.conditions.beginTime }&conditions['endTime']=${pages.conditions.endTime }"  />    
 
           </c:if>		 

	
  </div>
	 

</body>


<script>
var start = {
        elem: '#beginTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        // min: laydate.now(), //设定最小日期为当前日期
        max: '2019-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
          //  end.min = datas; //开始日选好后，重置结束日的最小日期
          // end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#endTime',
        format: 'YYYY-MM-DD hh:mm:ss',
       // min: laydate.now(),
        max: '2019-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function(datas){
         //  start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

</script>

<script>
 
$("#btnsearch").click(function(){
	
	  if (checkChar($("#content").val()) )
			  {
		          layer.msg('搜索条件含有非法字符！');
		          return false;
			  }
	  
  
	  $("#searchForm").submit();
	
});



	 
</script>



</html>
