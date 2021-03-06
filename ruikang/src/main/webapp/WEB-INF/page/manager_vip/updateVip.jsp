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

<title>理财客户详情</title>

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
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active">理财客户详情</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">理财客户详细信息</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					    <input type="hidden"  id="id"  name="id" value="${vipVo.vip.id}" >
					    
						<div class="form-group" style="margin-top:10px">
							<label for="name" class="col-sm-2 control-label">客户姓名:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="name"  name="name"  value="${ vipVo.vip.name}"   maxlength="20">
							</div>
						</div>
						
					 <div class="form-group">
							<label for="mobilenum" class="col-sm-2 control-label">手机号码:</label>
							<div class="col-sm-6">
								<input type="number" class="form-control" id="mobilenum"  name="mobilenum"  value="${ vipVo.vip.mobilenum}"  onkeyup="this.value=this.value.replace(/\D/g,'')"  maxlength="11"  size="14">
							</div>
						</div>
						
						 		 <div class="form-group">
							<label for="card" class="col-sm-2 control-label">身&nbsp;份&nbsp;证:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="card"  name="card"  value="${ vipVo.vip.card}"  maxlength="18"  size="18" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
							</div>
						</div>
						
						  <div class="form-group">
						  	<label for="sex" class="col-sm-2 control-label">客户性别:</label>
							  <div class="col-sm-6">
							    
							     <c:choose>
							           <c:when test="${ vipVo.vip.sex=='男'}">
							           
							               <label class="radio-inline">
											  <input type="radio" name="sex" id="sex" value="男"   checked="checked"> 男性
											</label>
											<label class="radio-inline">
											  <input type="radio" name="sex" id="sex" value="女"> 女性
											</label>
							           </c:when>
							           
							            <c:when test="${ vipVo.vip.sex=='女'}">
										           <label class="radio-inline">
														  <input type="radio" name="sex" id="sex" value="男"  > 男性
														</label>
														<label class="radio-inline">
														  <input type="radio" name="sex" id="sex"  value="女" checked="checked"> 女性
														</label>
							           </c:when>
							     
							     </c:choose>
							  
							 
								 
	                           </div>
                         </div>
                         
                         
                         	 <%--  <div class="form-group">
						  	<label for="level" class="col-sm-2 control-label">客户类型:</label>
							  <div class="col-sm-6">
							  
							  
							  <c:choose>
							           <c:when test="${ vipVo.vip.level==1}">
							           
							               <label class="radio-inline">
											  <input type="radio" name="level" id="level" value="1" checked="checked">都可访问
											</label>
											<label class="radio-inline">
											  <input type="radio" name="level" id="level" value="2"> 所属理财经理
											</label>
							           </c:when>
							           
							            <c:when test="${ vipVo.vip.level==2}">
										           <label class="radio-inline">
													  <input type="radio" name="level" id="level" value="1" >都可访问
													</label>
													<label class="radio-inline">
													  <input type="radio" name="level" id="level" value="2"   checked="checked"> 所属理财经理
													</label>
									
							           </c:when>
							     
							     </c:choose>
							     
							  
							      
								 
	                           </div>
                         </div> --%>
                         
                         <div class="form-group">
							<label for="description" class="col-sm-2 control-label">理财经理:</label>
							<div class="col-sm-6">
								   <select  id="managerid"   name="managerid"  class="  form-control"   >
              
							              
							                        <option value="0">全部</option>
							                       <c:forEach var="item" items="${users}">
							                   
							                      <option value=${item.id}>${item.realname}</option>
							                      
							                   </c:forEach>    
							            </select>
							</div>
						</div>
						
						
                         
                          		 <div class="form-group">
							<label for="bankname" class="col-sm-2 control-label">开户银行:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="bankname"  name="bankname"  value="${ vipVo.vip.bankname}"  maxlength="42"  >
							</div>
						</div>
						
						
						 		 <div class="form-group">
							<label for="banknum" class="col-sm-2 control-label">银行账号:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="banknum"  name="banknum"  value="${ vipVo.vip.banknum}"  maxlength="22"   onkeyup="this.value=this.value.replace(/\D/g,'')" >
							</div>
						</div>
						
						
								 <div class="form-group">
							<label for="address" class="col-sm-2 control-label">联系地址:</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control" id="address"  name="address" value="${ vipVo.vip.address}" maxlength="40"    >
							</div>
						</div>
						
						
						<!-- 	<div class="form-group">
							<label for="address" class="col-sm-2 control-label">出生日期:</label>
							<div class="col-sm-6">
								<input type="date" class="form-control" id="birthday"
									name="birthday">
							</div>
					    	</div> -->
					    	
					    	
						
								 <div class="form-group">
							<label for="memo" class="col-sm-2 control-label">备注信息:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="memo"  name="memo"  maxlength="400" style="text-align: left">${ vipVo.vip.memo}</textarea>
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

 //初始化 下拉框
function  initSelect()
{
	
	 $("#managerid").val(${vipVo.vip.managerid});
	
	}
	
initSelect();

$(function(){ 
    var options = {  
    	url:   "manager/updateVipAction.html"  ,
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
