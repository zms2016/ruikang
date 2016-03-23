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

<title>修改银行账户</title>

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
  <li><a href="javascript:void(0)">融资客户</a></li>
  <li class="active">修改银行账户</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">编辑银行账户信息</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					    <input type="hidden"  id="id"  name="id"  value="${bank.id }" >
					    
					    
						<div class="form-group" style="margin-top:10px">
							<label for="name" class="col-sm-2 control-label">账号名称:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="name"  name="name"   value="${bank.name }"  maxlength="40">
							</div>
						</div>
						
						
						     
                         <div class="form-group">
							<label for="description" class="col-sm-2 control-label">融资客户:</label>
							<div class="col-sm-6">
								   <select  id="rongziid"   name="rongziid"  class="  form-control"   >
            
                                                 
							                         <c:forEach var="item" items="${rzs}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
 
                          <div class="form-group">
							
							<label for="bankname" class="col-sm-2 control-label">开户银行:</label>
								<div class="col-sm-6">
									<input type="edit" class="form-control" id="bankname"  name="bankname"   value="${bank.bankname }"  maxlength="40">
								</div>
						</div>
						
						
						 		 <div class="form-group">
							<label for="banknum" class="col-sm-2 control-label">银行账号:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="banknum"  name="banknum"  value="${bank.banknum }"  maxlength="22"   onkeyup="this.value=this.value.replace(/\D/g,'')" >
							</div>
						</div>
						
						 
					    	
					    	
						
								 <div class="form-group">
							<label for="memo" class="col-sm-2 control-label">备注信息:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="memo"  name="memo" maxlength="400"> ${bank.memo}</textarea>
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

function  initSelect()
{
	
	$("#rongziid").val(${bank.rongziid});
	
	}
	
	initSelect();

$(function(){ 
    var options = {  
    	url:   "manager/updateRzBankAction.html"  ,
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
	     	warn(" 账号名称还没有填！","name",10,0)
        return false; 
    } 
	
	if( $("#bankname").val()=="" ){ 
     	warn(" 开户行还没有填！","bankname",10,0)
    return false; 
} 
	
	if( $("#banknum").val()=="" ){ 
     	warn(" 银行账号还没有填！","banknum",10,0)
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
