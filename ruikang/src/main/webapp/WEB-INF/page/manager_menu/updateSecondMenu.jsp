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

<title>修改二级菜单</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="js/jquery-form.js"></script>
<!-- 不用他
<script src="components/layer2/layer/layer.js"></script> -->
<script src="js/toast.js"></script>  

 <!-- 上传图片预览插件 -->
<!-- <script src="js/previewImage.js"></script> -->

</head>

<body>
       
       <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">菜单管理</a></li>
  <li class="active">修改二级菜单</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">编辑二级菜单</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					        <input type="hidden" id="id"  name="id"  value="${secondMenu.id}">
					    
							 <div class="form-group">
							<label for="description" class="col-sm-2 control-label">上级菜单:</label>
							<div class="col-sm-6">
								   <select  id="typeid"   name="typeid"  class="  form-control"   >
              
							              
							          
							                     <c:forEach var="item" items="${superMenuList}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>    >
							            </select>
							</div>
						</div>
						
						
						<div class="form-group" style="margin-top:10px">
							<label for="name" class="col-sm-2 control-label">菜单名称:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name"  name="name"  value="${secondMenu.name}">
							</div>
						</div>
						
						
							<div class="form-group" style="margin-top:10px">
							<label for="url" class="col-sm-2 control-label">url路径:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="url"  name="url"  value="${secondMenu.url}">
							</div>
						</div>
						
						
					 <div class="form-group">
							<label for="description" class="col-sm-2 control-label">菜单说明:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="description"  name="description" value="${secondMenu.description}">
							</div>
						</div>
						
						 		<!--    {"id":29,"typeid":8,"name":"创建合同","description":"创建合同","url":"/manager/addContract.html","isuse":true,"sortid":2} -->
						
						  <div class="form-group">
						  
						  
						  	<label for="isuse" class="col-sm-2 control-label">使用标志:</label>
							  <div class="col-sm-6">
							       <c:choose>
							           <c:when test="${secondMenu.isuse }">
											             <label class="radio-inline">
															  <input type="radio" name="isuse" id="isuse" value="1" checked="checked"> 显示
															</label>
															<label class="radio-inline">
															  <input type="radio" name="isuse" id="isuse" value="0"> 隐藏
															</label>
							           </c:when>
							           
							           <c:otherwise>
							                             <label class="radio-inline">
															  <input type="radio" name="isuse" id="isuse" value="1" > 显示
															</label>
															<label class="radio-inline">
															  <input type="radio" name="isuse" id="isuse" value="0" checked="checked"> 隐藏
															</label>
							           </c:otherwise>
							       
							       
							       </c:choose>
							  
								 
	                           </div>
                         </div>
                         
             
                          
						
						 		 <div class="form-group">
							<label for="sortid" class="col-sm-2 control-label">排序序号:</label>
							<div class="col-sm-6">
								<input type="number" class="form-control" id="sortid"  name="sortid"    value="${secondMenu.sortid}">
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

   function initSelect()
{
	  $("#typeid").val(${secondMenu.typeid})
	
	}
initSelect();


$(function(){ 
    var options = {  
    	url:   "manager/updateSecondMenuAction.html"  ,
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
	     	warn(" 菜单名称还没有填！","name",10,0)
        return false; 
    } 
 
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("更新成功！","name",100,0);
	    	 
	    	 }
	     else{
	    		warn("更新失败！","name",100,0);
	    	 
	     }
  
}  

</script>
</html>
