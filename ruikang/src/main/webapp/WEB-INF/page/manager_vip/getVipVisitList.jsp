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
<title>拜访记录列表</title>

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
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active">拜访记录列表</li>
</ol>

<div class="row" style="margin-top:20px;margin-left:20px">
 	<form id="searchForm"    action="${pageContext.request.contextPath}/manager/getVipVisitList.html" method="post"  class="form-inline">  
 	
 	                    
 	               <input type="hidden"  id="managerId"   name="managerId"  value="${managerId }">
 
	    	   <div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    for="vipname" >理财客户:</label>
                 <c:choose>
			   <c:when test="${ not empty pages.conditions.vipname }">    
			             	<input type="text" class="form-control"  value="${pages.conditions.vipname }"    id="vipname" name="conditions['vipname']"   maxlength="20" style="width:120px">
			   </c:when>   
			  
			   <c:otherwise>   
			               	<input type="text" class="form-control"    id="vipname"  name="conditions['vipname']"   maxlength="20" style="width:120px">
			   </c:otherwise>  
		</c:choose>
 
               
	</div> 
	
	
	    <c:choose>
                        <c:when test="${fn:length(managers)>1}">
                     
                           <div class="form-group form-inline">
									  <label style="font-size:1em;margin-left:15px"   for="managerId" >理财经理:  </label>
								        
									  <select   id="managerId"  name="conditions['managerId']"  class="  form-control"  style="margin-left:10px;width:120px"  >
									  
									     <option value="0">--全部--</option>
							                 
							                      <c:forEach var="manager" items="${managers}">
							                             <c:choose>
							                                   <c:when test="${manager.id==pages.conditions.managerId }">
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
									  <label style="font-size:1em;margin-left:15px"   for="managerId" >理财经理:</label>
								        
									  <select   id="managerId"  name="conditions['managerId']"  class="  form-control"  style="margin-left:10px; "  >
							               
							                   <option value="${managers[0].id}"> ${managers[0].realname } </option>  
							                       
														               
							            </select>
							 
							       </div>
                        
                        </c:otherwise>  
                   
                   </c:choose>
	  
	
	
	   <div class="form-group form-inline">
							<label for="visitType" >拜访类型:</label>
						 
								   <select  id="visitType"   name="conditions['visitType']"  class="  form-control"   >
              
                                            <option value="0">--全部--</option>
							                 <option value="1">当面拜访</option>
							                 <option value="2">电话回访</option>
							                 <option value="3">短信微信</option>
							                         
							                         
							            </select>
							 
						</div>
						
						
	
 
       <div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"     for="beginTime ">拜访时间:</label>
          
                  <input type="text"  class="form-control"  id="beginTime"   name="conditions['beginTime']"   value="${pages.conditions.beginTime}"   style="width:120px">
              
	  </div> 
	  
	  
	  	
						
						
	
			<div class="form-group form-inline">
	
	     <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="endTime ">时间结束:</label>
          
                  <input type="text"  class="form-control"  id="endTime"    name="conditions['endTime']"    value="${pages.conditions.endTime}"   style="width:120px">
              
	  </div>  
	  
	  
	  	      
 
 
		<div class="form-group form-inline">
	
	  <label style="font-size:1em;margin-left:15px"    class="sr-only"  for="searchBtn ">查询:</label>
          
          <button class="btn btn-primary" type="submit"   id="btnsearch">查询</button>
 
              	<a href="manager/addVipVisitInit.html"  class="btn btn-success"  style=" " >增加拜访记录</a>
	</div> 
	
	
 
 </form>
 
 
</div> 


 

 

	<div style="margin-left:16px; margin-top:20px;margin-bottom:90px;margin-right:30px">
		<table class="table table-striped  table-bordered table-hover">
			<thead  >
	<tr  style="background-color:#dcdcdc; ">
				<th>客户姓名</th>
		 
				<th>拜访时间</th>
				
				<th>拜访类型</th>
				
				<th>访谈内容</th>
				<th>备注信息</th>
			 
					 
					<th>操作</th>  
				</tr>
			</thead>

			<tbody>
 
			 
				<c:forEach var="item" items="${pages.pageDatas}">
			 
					<tr id="tr_${item.visitId }">
						<td><c:out value="${item.vipName}" /></td>
						
						<td>  <fmt:formatDate value="${item.visitTime}" pattern="yyyy年MM月dd日" /> </td>
						
						
						 <td> ${item.visitType}</td>
						 
			<%-- 			    <c:choose>
						        <c:when test="${item.visitType==1 }">
						               <td>  当面拜访</td>
						        </c:when>
						        
						            <c:when test="${item.visitType==2 }">
						          <td>  电话回访</td>
						        </c:when>
						        
						            <c:when test="${item.visitType==3 }">
						                 <td>短信微信</td>
						        </c:when>
						        
						        
						    </c:choose> --%>
						
                        <td  >  <div class="autocut" style="width:400px;  ">    ${item.content}</div>    </td>
		                  <td>   <div class="autocut" style=”width:250px">    ${item.memo} </div>   </td>				 
				 
						<td>
						    <a href="manager/getVipVisitInfo.html?visitId=${item.visitId}"> 详情 </a>
							<a href="javascript:void(0)" onclick="deleteById(${item.visitId })"> 删除 </a>
						</td>  
					</tr>
				</c:forEach>
			</tbody>
		</table>

 
		 		<!--  使用自己的 分页标签  前面三个是固定的必须参数 后面的url  是请求路径，可以根据情况  加或者不加参数-->
		
		   <c:if test="${pages.totalPages>1 }"> 
                  <page:pager pageSize="${pages.pageSize}"  currPage="${pages.currPage}" totalRecords="${pages.totalRecords}" 
                   url="${pageContext.request.contextPath}/manager/getVipVisitList.html?conditions['visitType']=${pages.conditions.visitType }&conditions['vipname']=${pages.conditions.vipname}&conditions['managerId']=${pages.conditions.managerId}&conditions['beginTime']=${pages.conditions.beginTime }&conditions['endTime']=${pages.conditions.endTime }"  />    
 
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


$("#visitType").val(${pages.conditions.visitType});

 



$("#btnsearch").click(function(){
	
	  if (checkChar($("#vipname").val()) )
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
			url : '${pageContext.request.contextPath}/manager/deleteVipVisitById.html',
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
