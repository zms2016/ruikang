<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>增加用户</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="js/jquery-form.js"></script>

<script src="js/zmsTools.js"></script>  

<!-- 不用他
<script src="components/layer2/layer/layer.js"></script> -->
<script src="js/toast.js"></script>  

 <!-- 上传图片预览插件 -->
<script src="js/previewImage.js"></script>

</head>

<body>

       <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">用户管理</a></li>
  <li class="active">增加用户</li>
</ol>

 
	<div class="row" style="margin:5px">
		<div class="col-md-6  ">

			<div class="panel panel-default">
				<div class="panel-heading">增加用户</div>
				<div class="panel-body">

					<form class="form-horizontal"    enctype="multipart/form-data"    id="addUserForm"   >
						<div class="form-group" style="margin-top:10px">
							<label for="username" class="col-sm-2 control-label">登录账号:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="username"  name="username"   onkeyup="value=value.replace(/[^A-Za-z0-9_-]*$/g,'')" maxlength="20">
							</div>
									<div class="col-sm-4">   
									
									<span style="    line-height: 34px;  text-decoration: underline; color: #248032;">只能是英文字母和下划线组成</span>     
							     </div>
						</div>
						
									<div class="form-group" style="margin-top:10px">
							<label for="realname" class="col-sm-2 control-label"> 真实姓名:</label>
							<div class="col-sm-4">
								<input type="edit" class="form-control" id="realname"  name="realname"  maxlength="20">
							</div>
						</div>
						
						
						
								<div class="form-group" style="margin-top:10px">
							<label for="password" class="col-sm-2 control-label">登录密码 :</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="password" name="password"    maxlength="18" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
							</div>
								<div class="col-sm-4">
								   	<span style="    line-height: 34px;  text-decoration: underline; color: #248032;"> 长度在18位以内的数字和字母组合</span>  
							     </div>
						</div>
						

					 
						
						           <div class="form-group">
							<label for="department" class="col-sm-2 control-label">部门名称:</label>
							<div class="col-sm-4">
								   <select  id="departmentid"   name="departmentid"  class="  form-control"   >
                                                        
                                                     
							              
							                      
							                       <c:forEach var="item" items="${departmentList}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
						
						

						<!-- <div class="form-group">
							<label for="address" class="col-sm-2 control-label">出生日期:</label>
							<div class="col-sm-4">
								<input type="date" class="form-control" id="birthday"
									name="birthday">
							</div>
						</div> -->
 

          <!--                  <div class="form-group  ">

							                
							                   <label for="picFile"  class="col-sm-2 control-label">设置用户头像:</label> <br>
							
							                 	<div class="col-sm-4">
							                   <input type="file" class="form-control"    id="picFile"   name="picFile">
							                   </div>
							             <div class="col-sm-4"    margin-left: 5px;">
							                  
							                    <img id="ImgPr" width="120" height="120"     src="images/pic_null.png"  style="border: 1px solid #B9B1B1; margin: 5px;"/>
							                     
							                
							             </div>
							 
							   </div> -->
                      
                       
                        <div style="margin-left:20px;margin-bottom:40px">
                        <strong>系统角色: </strong>  <br>
                        <c:forEach   var="role"  items="${roleList }" varStatus="index"> 
                        
                            <label class="checkbox-inline">
					            <input type="checkbox"  id="${role.id }"    value="${role.id }"  name="roleids"> ${role.name }
					        </label>
					        
					          <c:if test="${index.count%6==0 }">
					              </br>
					          </c:if>
					        
                        </c:forEach>  
				
			        </div>
			 
 

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary  col-sm-2"  id="subBtn">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
 

</body>


<script>

  //图片预览
$(function () {
	   $("#picFile").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
	   });
	   
 
</script>

<script>

$(function(){ 
    var options = {  
    	url:   "manager/addUserAction.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: true,    //把表单清空 ，由于有图片预览，需要在 showResponse 里单独处理
        dataType:  "json",
   
    };  
  
    $("#addUserForm").submit(function() {  
          
    	    $(this).ajaxSubmit(options);  
    	    // 阻止默认的提交事件
    	    return false;
    	    
    });  
}); 
 
 //提交前验证
function showRequest(formData, jqForm, options) {  
    
 
	if( $("#username").val()=="" ){ 
	     	warn(" 登陆账号还没填！","username",10,0)
        return false; 
    } 
	
	if( $("#realname").val()=="" ){ 
     	warn(" 真实姓名还没填！","realname",10,0)
    return false; 
} 
	
/* 	if($("#realname").val()==""){ 
     	warn("真实姓名没有填！","realname",10,20)
    return false; 
}  */
	
	if($("#password").val()==""){ 
     	warn("密码还没填！","password",10,0)
    return false; 
} 

 
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="insertOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("新增成功！","subBtn",100,0);
	    	 $("#ImgPr").attr("src","images/pic_null.png");
	    	 }
	     else{
	    		warn("新增失败！","subBtn",100,0);
	    	 
	     }
  
}  

</script>
</html>
