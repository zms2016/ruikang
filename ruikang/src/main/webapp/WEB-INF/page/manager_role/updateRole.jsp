<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<body>
 
		  
		   <div class="panel panel-primary" style="margin-bottom: 0px;border: none"  id="roleeditpanel">
			 <div class="panel-heading">修改角色</div>
			 <div class="panel-body">

			 
					 <div class="form-group" style="margin-top:10px">
						 <label for="rolename" class="col-sm-2 control-label">角色名称 :</label>
						 <div class="col-sm-4">
							 <input type="edit" class="form-control" id="rolename"  name="rolename"  value="${role.name }" maxlength="20">
						 </div>
					 </div>

					 <div class="form-group">
						 <label for="roledescription" class="col-sm-2 control-label">角色描述:</label>
						 <div class="col-sm-4">
							 <input type="text" class="form-control" id="roledescription"
									name="roledescription"   value="${role.description }" maxlength="40">
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
							 <button type="button" class="btn btn-default  " id="editbtncancel">取消</button>
						 </div>

						 <div  style="float:left;margin-left: 40px">
							 <button type="button" class="btn btn-primary " id="editbtnok">提交</button>
						 </div>
					 </div>
			 
			 </div>
		 </div>
		  
		  
		  
 </body>
 
 <script>
 
 var modules =new Array();
 
 
  /*   把获取到的 角色拥有的大模块 */
   function init() {
 
	   
	  
	  modules="${role.rightids}".split(",");
          
	    $("#roleeditpanel :checkbox").each(function() {
	    	
	      if ($.inArray($(this).val(),modules)>-1) {
	    	  
	        $(this).attr("checked","checked");
	      }
	    });
	  }
 
  //初始化 选择框 该打勾的打勾
 init();  
 
$("#editbtncancel").on('click',function()
{
	 
	layer.close(editlay);
})


$("#editbtnok").on('click',function()
{
	
 
	/* 把 checkbox的选择项，拼凑成一个 字符串 用 ，号隔开 */
    var arr = new Array();
    $("#roleeditpanel :checkbox:checked").each(function() {
      arr.push($(this).val());
    });
    var ids = arr.join(",");
	 
 
    	 $.ajax({
 			type : 'POST',
 			url : '${pageContext.request.contextPath}/manager/updateRoleAction.html',
 	        data : { 
 	          "id":${role.id},
	 	   	  "name":$("#rolename").val(),
	    	  "description":$("#roledescription").val(),
 			  "rightids":ids
 	    	  
 	      },
 	      dataType:'json',  //要求服务器返回 json对象 而不是字符串
 	      success : function(data) {
 	      
 	               if  (data.msg=="updateok")
 	            	   {
 	            	      
 	            	         layer.msg("修改成功");
 	            	       
 	            	         var  htmlstr="  <td>"+ ${role.id} +"</td><td>"+  $("#rolename").val() +"</td> <td>"+$("#roledescription").val() +"</td><td>"
   	                       +"<a href='javascript:void(0)'   onclick='editrole("+${role.id} +" )' >编辑</a> "
   	                       +"<a href='javascript:void(0)'   onclick='setRoleRights("+${role.id} +" )' >设置权限</a> "
   	                       +"<a href='javascript:void(0)'   onclick='xx("+${role.id} +" )' >管理角色人员</a> "
   	                       +"<a href='javascript:void(0)'   onclick='deleterole("+${role.id} +" )' >删除</a> "
   	                       +" </td> ";
   	                       
   	                    
   	                       /*   修改列表中的数据  不需要刷新页面 */
   	                       $("#tr_"+${role.id}).html(htmlstr);
 	            	      
 	    	    	        layer.close(editlay)
 	    	    	
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
 