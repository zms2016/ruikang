<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<body>
 
 

		   <div class="panel panel-primary" style="margin-bottom: 0px;border: none"  id="addrolepanel">
			 <div class="panel-heading">增加角色</div>
			 <div class="panel-body">

			 
					 <div class="form-group" style="margin-top:15px">
						 <label for="name" class="col-sm-2 control-label" style="text-align:right;padding-right: 0px;">角色名称 :</label>
						 <div class="col-sm-4">
							 <input type="text" class="form-control" id="name"  name="name"  maxlength="20">
						 </div>
					 </div>

					 <div class="form-group">
						 <label for="description" class="col-sm-2 control-label" style="text-align:right;padding-right: 12px;">角色描述:</label>
						 <div class="col-sm-4" style="padding-left:0px">
							 <input type="text" class="form-control" id="description"  name="description"   maxlength="40">
						 </div>
					 </div>
                       
                       
                       <div style="clear: both;"></div>
                       
                          
                           <br>
                       
                        <div  style="    border: 1px dashed #dadada;">
                        
				         <h3 style="margin-left:10px"> 权限列表:</h3>
				         
                              <hr style="height: 1px;border: none; border-top: 1px dashed #dadada">
                              
                               <div style="padding:20px">
                               
                                  <c:forEach var="jui"  items="${types}" varStatus="index">
				                    
				                      <label class="checkbox-inline">
								       <input type="checkbox"   value="${jui.id }" name="check"> ${jui.name }
							          </label>
							             
							             <!-- 每行显示多少个， 超过的话换行 -->
				                     <c:if test="${index.count % 4==0 }">
							       
							            <br>
							            
							          </c:if>
							  
							 
				                </c:forEach>
				                
                            </div>
                            
                            </div>
                
                
						 </div>
					 </div>


					 <div >


						 <div  style="float:left;margin-left: 40px"  >
							 <button type="button" class="btn btn-default  " id="btncancel">取消</button>
						 </div>

						 <div  style="float:left;margin-left: 40px">
							 <button type="button" class="btn btn-primary " id="btnok">提交</button>
						 </div>
					 </div>
			 
			 </div>
		 </div>
		  
		  
		  
 </body>
 
 <script>
 

 
$("#btncancel").on('click',function()
{
	layer.close(addlay);
})


$("#btnok").on('click',function()
{
 
 
	/* 把 checkbox的选择项，拼凑成一个 字符串 用 ，号隔开 */
    var arr = new Array();
         
    $("#addrolepanel :checkbox:checked").each(function() {
      arr.push($(this).val());
    });
    var ids = arr.join(",");
	 
     
    
	  $.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/addRoleAction.html',
	        data : { 
	    	  "name":$("#name").val(),
	    	  "description":$("#description").val(),
			  "rightids":ids
			  
	      },
	      dataType:'json',  //要求服务器返回 json对象 而不是字符串
	      success : function(data) {
	      
	              /*  添加成功后，从后台获取到 插入的 id，返回到前台， 前台不需要刷新页面重新读取数据库，直接操作列表，把数据增加到表格中 */
	    	     
	    	     if (data.msg=="ok")
	    	    	 {
	    	    	 layer.msg("增加成功！");
	    	    	 
	    	    	  var  htmlstr=" <tr id='tr_"+data.code+"'><td>"+ data.code +"</td><td>"+  $("#name").val() +"</td> <td>"+$("#description").val() +"</td><td>"
	    	    	                       +"<a href='javascript:void(0)'   onclick='editrole("+data.code+" )' >编辑</a> "
	    	    	                       +"<a href='javascript:void(0)'   onclick='setRoleRights("+data.code+" )' >设置权限</a> "
	    	    	                       +"<a href='javascript:void(0)'   onclick='xx("+data.code+" )' >管理角色人员</a> "
	    	    	                       +"<a href='javascript:void(0)'   onclick='deleterole("+data.code+" )' >删除</a> "
	    	    	                       +" </td></tr>";
	    	    	                    
	    	    	      /* append是插入到最后一行，  prepend是插入到第一行，根据业务需求 选择插入的方式 */
	    	    	      
	    	    	   $("#tbody").append(  htmlstr );
	    	    	   
	    	    	 layer.close(addlay);
	    	    	
	    	    	 }
	    	     else
	    	    	 {
	    	    	 layer.msg("增加失败！");
	    	    	 }
	    	     
	    	 
	    		
	    	   
	    	   
	      },
	      error : function() {
	        alert("对不起，出现错误!");
	      }
	    });
})


 
 </script>
 </html>
 