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

<title>创建合同</title>

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
  <li><a href="javascript:void(0)">项目管理</a></li>
  <li class="active">创建合同</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">批量创建合同</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
						<div class="form-group" style="margin-top:10px">
							<label for="preFix" class="col-sm-2 control-label">合同前缀:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="preFix"  name="preFix"  placeholder="必填"  maxlength="20">
							</div>
						</div>
						
						
						     
                         <div class="form-group">
							<label for="rzId" class="col-sm-2 control-label">融资客户:</label>
							<div class="col-sm-6">
								   <select  id="rzId"   name="rzId"  class="  form-control"   onchange="rzChange()" >
            
                                                      <option value="0">请选择</option>
							                         <c:forEach var="item" items="${rongzis}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
						
						
						
								  
			             <!--          <div class="form-group">
							<label for="rzbankid" class="col-sm-2 control-label">收款账户:</label>
							<div class="col-sm-6">
								   <select  id="rzbankid"   name="rzbankid"  class="  form-control"   >
            
                                                 <option value="0">请选择收款账户</option>
							                       
							            </select>
							</div>
						</div>   -->
						
 
                          <div class="form-group">
							
							<label for="startNum" class="col-sm-2 control-label">启始编号:</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="startNum"  name="startNum" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="4" size="14" >
								</div>
						</div>
						
						    <div class="form-group">
							
							<label for="count" class="col-sm-2 control-label">印发数量:</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="count"  name="count" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="4" size="14" >
								</div>
						</div>
						
						
					 
	 
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary  col-sm-2"  id="subBtn">创建合同</button>
							</div>
						</div>
					</form>
				</div>
			</div>
 
		</div>
	</div>


</body>

 <script>
 
//融资项目 下拉列表 变动事件  弃用 2015-11-16
 
 function investChange( )
 {
     if ( $("#investNum").val()=="0")
    	 {
    	  $("#rzbankid").html("<option value='0'>请选择</option");
    	 
    	 } else
    		 {
    		 
    		  $.ajax({
   				type : 'POST',
   				url : '${pageContext.request.contextPath}/manager/getBankByInvest.html',
   				data : {"id" : $("#investNum").val()},
   			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
   				success : function(arr) {
   				    var rzbankid="";
   				    var length = arr.length;
   				    for(var i = 0; i < length; i ++){
   				    	 
   				    	rzbankid = rzbankid + "<option value="+arr[i].id+">"+arr[i].name+"</option>";
   				    }
   				   $("#rzbankid").html(rzbankid);
   					 
   				},
   				error : function() {
   					alert("出错");
   				}

   			})
    		 
    		 }
 		
  
 }  
 
 </script>
 

<script>

$(function(){ 
    var options = {  
    	url:   "manager/createContractAction.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: true,    //把表单清空 ，由于有图片预览，需要在 showResponse 里单独处理
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
    
 
	if( $("#preFix").val()=="" ){ 
	     	warn(" 合同前缀还没填！","preFix",10,0)
        return false; 
    } 
	
	if( $("#investNum").val()=="0" ){ 
     	warn(" 融资项目还没选！","investNum",10,0)
    return false; 
} 
	
	if( $("#startNum").val()=="" ){ 
     	warn(" 起始编号没填！","startNum",10,0)
    return false; 
} 
	
	if( $("#count").val()=="" ){ 
     	warn(" 合同份数没填！","count",10,0)
    return false; 
} 
	
	if( $("#rzbankid").val()=="0" ){ 
     	warn(" 收款银行没有填！","rzbankid",10,0)
    return false; 
} 
	
 
	
	
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="insertOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("合同创建成功！","preFix",100,0);
	     
	    	 }
	     else{
	    		warn("合同创建失败！","preFix",100,0);
	    	 
	     }
  
}  

</script>
</html>
