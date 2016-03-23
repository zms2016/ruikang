<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<title>用户编辑</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="js/jquery-form.js"></script>

<!-- <script src="js/toast.js"></script>   -->
<script src="components/layer2/layer/layer.js"></script> 

<script src="components/bootstrap/js/bootstrap.min.js"></script>

 <!-- 上传图片预览插件 -->
<script src="js/previewImage.js"></script>
</head>

<body>


        <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">用户管理</a></li>
  <li class="active">更改用户信息</li>
</ol>


 
	<div class="row" style="margin:5px">
		<div class="col-md-6  " style="    padding-left: 1px;">


			<div class="panel panel-default">
				<div class="panel-heading">用户信息</div>
				<div class="panel-body">

					<form class="form-horizontal"     enctype="multipart/form-data"   id="updateUserForm"   name="updateUserForm">

						<input type="hidden" name="id" value="${user.id }">

				<div class="form-group" style="margin-top:10px">
							<label for="username" class="col-sm-2 control-label">登录账号:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="username"  name="username"   onkeyup="value=value.replace(/[^A-Za-z0-9_-]*$/g,'')" maxlength="20"  value="${user.username}">
							</div>
									<div class="col-sm-4">   
									
									<span style="    line-height: 34px;  text-decoration: underline; color: #248032;">只能是英文字母和下划线组成</span>     
							     </div>
						</div>

						 
						
							<div class="form-group" style="margin-top:10px">
							<label for="realname" class="col-sm-2 control-label">真实姓名:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="realname"  name="realname" value="${user.realname}" maxlength="20">
							</div>
						</div>
						
						
						
					 					<div class="form-group" style="margin-top:10px">
							<label for="password" class="col-sm-2 control-label">登录密码 :</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="password" name="password"    maxlength="18" onkeyup="value=value.replace(/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/,'')" value="${user.password }">
							</div>
								<div class="col-sm-4">
								   	<span style="    line-height: 34px;  text-decoration: underline; color: #248032;"> 长度在6-22之间</span>  
							     </div>
						</div>
						  
						
						 
						
						

						           <div class="form-group">
							<label for="department" class="col-sm-2 control-label">部门名称:</label>
							<div class="col-sm-4">
								   <select  id="departmentid"   name="departmentid"  class="  form-control"   >
                                                        
                                                   
							              
							                     <option value="0">全部</option>
							                       <c:forEach var="item" items="${departmentList}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>    
							            </select>
							</div>
						</div>
					 
 
					<%-- 	<div class="form-group">
							<label for="birthday" class="col-sm-2 control-label">出生日期:</label>
							<div class="col-sm-4">
								<input type="date" class="form-control"   id="birthday"
									name="birthday"  value="<fmt:formatDate value="${user.birthday}"   pattern="yyyy-MM-dd" />">
							</div>
						</div> --%>

                                <%-- <div class="form-group  ">

							                
							                   <label for="picFile"  class="col-sm-2 control-label">设置用户头像:</label> <br>
							
							                 	<div class="col-sm-4">
							                   <input type="file" class="form-control"    id="picFile"   name="picFile">
							                   </div>
							             <div class="col-sm-4"    margin-left: 5px;">
							                  
							                     <c:if test="${user.pic!=null }">
							                      <div><img id="ImgPr" width="120" height="120"       style="border: 1px solid #B9B1B1; margin: 5px;" src="/userFacePic/${user.pic }"/></div>
							                      
							                     </c:if>
							                     
							                         <c:if test="${user.pic==null }">
							                      <div><img id="ImgPr" width="120" height="120"       style="border: 1px solid #B9B1B1; margin: 5px;" src="images/pic_default.png"/></div>
							                      
							                     </c:if>
							                     
							                
							             </div>
							 
							   </div> --%>
                             
                            <%--  	<div class="form-group">
							<label for="picFile" class="col-sm-2 control-label">图片地址:</label>
							<div class="col-sm-4">
								<input type="file" class="form-control"    id="picFile"   name="picFile">
								
										<div><img id="ImgPr" width="120" height="120"  src="/userFacePic/${user.pic}"  /> </div>
							</div>
						</div> --%>
						
							<div class="form-group">
							 
							 	<label class="col-sm-2 control-label">选择角色:</label>
							
							<div class="col-sm-4">
						  <div style="margin-left:20px;margin-bottom:40px"  id="roleslist">
                              <c:forEach   var="role"  items="${roleList }" varStatus="index"> 
                        
                            <label class="checkbox-inline">
					            <input type="checkbox"  id="${role.id }"    value="${role.id }"  name="roleids"> ${role.name }
					        </label>
					        
					          <c:if test="${index.count%6==0 }">
					              </br>
					          </c:if>
					        
                        </c:forEach>  
				
			        </div>
			        </div>
			        </div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary   col-sm-4"  id="subBtn">保&nbsp;&nbsp;存</button>
							</div>
						</div>
					</form>

				</div>
			</div>


		</div>
	</div>
	
	
	<script>

$(function(){ 
    var options = {  
    	url:   "manager/updateUserAction.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: false,    // 这里是更新 不是 新增， 所以最好不要重置表单数据
        dataType:  "json",
   
    };  
  
    $("#updateUserForm").submit(function() {  
          
    	    $(this).ajaxSubmit(options);  
    	    // 阻止默认的提交事件
    	    return false;
    	    
    });  
}); 
 
 //提交前验证
function showRequest(formData, jqForm, options) {  
    
 
/* 	if( $("#username").val()=="" ){ 
	     	warn(" 登陆账号还没填！","username",10,0)
        return false; 
    } 
	
 	if($("#realname").val()==""){ 
     	warn("真实姓名没有填！","realname",10,20)
    return false; 
}   
	
	if($("#password").val()==""){ 
     	warn("密码还没填！","password",10,0)
    return false; 
}  */
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		//warn("修改成功！","subBtn",100,0);
	    	     layer.msg("修改成功!");
	    	  
	    	 }
	     else{
	    	//	warn("修改失败！","subBtn",100,0);
	    	   layer.msg("修改失败!");
	    	 
	     }
  
}  

</script>




		<script>
		
				var  modules =new Array();
				
				function init()
				{
				
			          modules="${roleIds}".split(",");
				         
					    $("#roleslist :checkbox").each(function() {
					    	
					      if ($.inArray($(this).val(),modules)>-1) {
					    	  
					        $(this).attr("checked","checked");
					      }
					    });
					    
					    
					    
					    
			  		   $("#departmentid").val(${user.departmentid});  
					    
					    
					    
					
				}
				
				init();
		
		
				 /*  图片预览 */
		 
			/* 	$(function () {
					   $("#picFile").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
					   }); */
				
				
		</script>

</body>
</html>
