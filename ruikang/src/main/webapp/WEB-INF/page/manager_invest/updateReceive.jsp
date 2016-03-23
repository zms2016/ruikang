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
  <li><a href="javascript:void(0)">项目管理</a></li>
  <li class="active">登记收款信息</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">登记收款信息</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					  <input type="hidden" id="lastflag" name="lastflag"  value="${vo.lastflag}">
					    <input type="hidden" id="id" name="id"  value="${vo.id}">
				    <input type="hidden" id="investid" name="investid"  value="${vo.investid}">
				    <input type="hidden" id="backmoney" name="backmoney"  value="${vo.backmoney}">
						
						<div class="form-group" style="margin-top:10px">
							<label for="investid" class="col-sm-2 control-label">项目编号</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control"   id="id"  name="id"   readonly    value="${vo.id }">
							</div>
						</div>
						
						
										<div class="form-group" style="margin-top:10px">
							<label for="investid" class="col-sm-2 control-label">收款金额</label>
							<div class="col-sm-6">
								<input type="edit" class="form-control"   id="id"  name="id"   readonly    value="<fmt:formatNumber value="${vo.backmoney }" type="currency" pattern="#00.00"/>">
							</div>
						</div>
						
	  
					     <div class="form-group">
							<label for="realtime" class="col-sm-2 control-label">收款时间</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="realtime"   name="realtime"   onclick="laydate()"  value="<fmt:formatDate value="${vo.backtime}" pattern="yyyy-MM-dd" />">
							</div>
						</div>
						
						       <div class="form-group">
						             <!--  还款标识标志 0：还未到还款时间  1 已经还款  2 逾期  4异常-->
							<label for="backflag" class="col-sm-2 control-label">收款状态</label>
							<div class="col-sm-6">
								   <select  id="backflag"   name=backflag  class="  form-control"   >
            
                                                  <option value="1">已经收款</option>
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
    	url:   "manager/updateReceiveAction.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: true,    //把表单清空  
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
    
 
	 
	
  	if( $("#backtime").val()=="" ){ 
     	warn(" 还款时间还没选！","backtime",10,0)
    return false; 
} 
 
 
	
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    	 
	    		warn("更新成功！","backflag",20,0);
	    	 }
	     else if(responseText.msg="hasBack")
	    	 {
	    		warn("该记录已经登记还款！请别重复提交！","backflag",20,0);
	    	 }
	     else{
	    		warn("更新失败！","backflag",20,0);
	    	 
	     }
  
}  

</script>
</html>
