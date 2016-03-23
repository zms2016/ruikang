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

<title>编辑融资客户</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="js/jquery-form.js"></script>
<!-- 不用他
<script src="components/layer2/layer/layer.js"></script> -->
<script src="js/toast.js"></script>  

 <!-- 上传图片预览插件 -->
<script src="js/previewImage.js"></script>

</head>

<body>
       
       <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">融资客户</a></li>
  <li class="active">编辑融资客户</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">编辑融资客户信息 </div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					   <input type="hidden"  id="id"  name="id" value="${rongzi.id}" >
					   
					   
						<div class="form-group" style="margin-top:10px">
							<label for="name" class="col-sm-2 control-label">客户姓名:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name"  name="name"   value="${rongzi.name }" maxlength="20">
							</div>
						</div>
						
					 <div class="form-group">
							<label for="mobilenum" class="col-sm-2 control-label">手机号码:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="mobilenum"  name="mobilenum"  value="${rongzi.mobilenum }"  maxlength="11"   onkeyup="this.value=this.value.replace(/\D/g,'')" >
							</div>
						</div>
						
						
							 <div class="form-group">
							<label for="card" class="col-sm-2 control-label">身&nbsp;份&nbsp;证:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="card"  name="card"  value="${rongzi.card }"   maxlength="18"   onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" >
							</div>
						</div>
						
						
						 	 
                         
                 <%--         <div class="form-group">
							<label for="description" class="col-sm-2 control-label">理财经理:</label>
							<div class="col-sm-6">
								   <select  id="typeid"   name="typeid"  class="  form-control"   >
              
							              
							                         
							                       <c:forEach var="item" items="${users}">
							                   
							                      <option value=${item.id}>${item.realname}</option>
							                      
							                   </c:forEach>    
							            </select>
							</div>
						</div> --%>
						
						
                          
						
						
								 <div class="form-group">
							<label for="address" class="col-sm-2 control-label">联系地址:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="address"  name="address"  value="${rongzi.address }"  maxlength="40">
							</div>
						</div>
						
					 
					    	
					    	
						
								 <div class="form-group">
							<label for="memo" class="col-sm-2 control-label">备注信息:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="memo"  maxlength="400"  name="memo">${rongzi.memo }</textarea>
							</div>
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

 

$(function(){ 
    var options = {  
    	url:   "manager/updateRongziAction.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: false,    //把表单清空 ，由于有图片预览，需要在 showResponse 里单独处理
        dataType:  "json",
   
    };  
  
    $("#webForm").submit(function() {  
          
    	    $(this).ajaxSubmit(options);  
    	    // 阻止默认的提交事件
    	    return false;
    	    
    });  
}); 
 
 //提交前验证
function showRequest(formData, jqForm, options) {  
    
 
	if( $("#name").val()=="" ){ 
	     	warn(" 用户名称还没有填！","name",10,0)
        return false; 
    } 
	
 
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("更新成功！","card",100,0);
	     
	    	 }
	     else{
	    		warn("更新失败！","card",100,0);
	    	 
	     }
  
}  

</script>
</html>
