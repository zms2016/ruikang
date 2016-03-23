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

<title>编辑还款信息</title>

 

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

     

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="components/laydate/laydate.js"></script>

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
  <li><a href="javascript:void(0)">财务核算</a></li>
  <li class="active">编辑还款信息</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">编辑还款信息</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					  
					  <inpu type="hidden" id="lastflag" name="lastflag"  value="${vo.schedule.lastflag}">
					   
					 
					     		<div class="form-group" style="margin-top:10px">
							<label for="contractid" class="col-sm-2 control-label">序号</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control"   id="id"  name="id"   readonly    value="${vo.schedule.id }">
							</div>
						</div>
						
					   
						<div class="form-group" style="margin-top:10px">
							<label for="contractid" class="col-sm-2 control-label">合同编号</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control"   id="contractid"  name="contractid"   readonly    value="${vo.schedule.contractid }">
							</div>
						</div>
	  
		 
					     <div class="form-group">
							<label for="backtime" class="col-sm-2 control-label">还款时间</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="backtime"   name="backtime"   onclick="laydate()"  value="${vo.backTime }">
							</div>
						</div>
						
						       <div class="form-group">
						             <!--  还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常-->
							<label for="flag" class="col-sm-2 control-label">还款状态</label>
							<div class="col-sm-6">
								   <select  id="flag"   name="flag"  class="  form-control"   >
            
                                                  <option value="1">已经还款</option>
                                                  <option value="2">逾期</option>
                                                  <option value="4">异常</option>
							                        
							            </select>
							</div>
						</div>
						
					 
	 
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary  col-sm-2"  id="subBtn">确 定</button>
							</div>
						</div>
					</form>
				</div>
			</div>
 
		</div>
	</div>


</body>

 
 <script>

     laydate({

    event: 'click', //触发事件
    format: 'YYYY-MM-DD', //日期格式
    istime: false, //是否开启时间选择
    isclear: true, //是否显示清空
    istoday: true, //是否显示今天
    issure: false, //是否显示确认
    festival: true ,//是否显示节日
    min: '2015-01-01 00:00:00', //最小日期
    max: '2019-12-31 23:59:59', //最大日期
    //start: '2014-6-15 23:00:00',    //开始日期
    fixed: false, //是否固定在可视区域
    zIndex: 99999999 //css z-index
  /*  choose: function(dates){ }//选择好日期的回调
    */
             }
       );


</script>
 
 

<script>

$(function(){ 
    var options = {  
    	url:   "manager/updateScheduleAction.html"  ,
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
    
 
	 
	
/* 	if( $("#investNum").val()=="0" ){ 
     	warn(" 融资项目还没选！","investNum",10,0)
    return false; 
} 
  */
	
	
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("还款信息修改成功！","contractid",100,0);
	     
	    	 }
	     else{
	    		warn("还款信息修改失败！","contractid",100,0);
	    	 
	     }
  
}  

</script>
</html>
