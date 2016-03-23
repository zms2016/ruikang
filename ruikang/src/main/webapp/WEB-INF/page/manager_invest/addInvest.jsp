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

<title>增加融资项目</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

  <!--   <link  rel="stylesheet" href="components/jqueryui/jquery-ui.min.css"> -->

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="components/laydate/laydate.js"></script>

<!--     <script src="components/jqueryui/jquery-ui.min.js"></script> -->

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
  <li><a href="javascript:void(0)">融资项目</a></li>
  <li class="active">新增融资项目</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">融资项目信息录入</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
						
						
						<div class="form-group" style="margin-top:10px">
							<label for="name" class="col-sm-2 control-label">项目名称:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name"  name="name"  placeholder="必填" maxlength="40">
							</div>
						</div>
						
						
						<div class="form-group" style="margin-top:10px">
							<label for="investsid" class="col-sm-2 control-label">项目编号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="investsid"  name="investsid"  placeholder="必填" maxlength="40">
							</div>
						</div>
						
						
						
						     
                         <div class="form-group">
							<label for="description" class="col-sm-2 control-label">融资客户:</label>
							<div class="col-sm-6">
								   <select  id="rongziid"   name="rongziid"  class="  form-control"  onchange="rzIdChanged()"  >
                                                    <option value="0">请选择</option>
                                                 
							                         <c:forEach var="item" items="${rzs}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
						
						
						          <div class="form-group">
							
							<label for="rzmoney" class="col-sm-2 control-label">融资金额:</label>
								<div class="col-sm-6">
								
						 
									<input type="text" class="form-control" id="rzmoney"  name="rzmoney" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="8"  size="14" >
								</div>
						</div>
						
						
						
						                 <div class="form-group">
						             <!--     '还款方式 0 先息后本 1 等额本息 2 一次性还款付息', -->
							<label for="backtype" class="col-sm-2 control-label">还款方式</label>
							<div class="col-sm-6">
								   <select  id="backtype"   name="backtype"  class="  form-control"   >
            
                                                  <option value="0">先息后本</option>
                                                  <!-- <option value="1">等额本金</option> -->
                                                  <option value="2">一次性还款付息</option>
							                        
							            </select>
							</div>
						</div>
						
		 
						               <div class="form-group">
							<label for="rzbankid" class="col-sm-2 control-label">银行账户:</label>
							<div class="col-sm-6">
								   <select  id="rzbankid"   name="rzbankid"  class="  form-control"   >
            
                                                 <option value="0" >请选择银行账户</option>
                                 
							            </select>
							</div>
						</div>
						
						
						 		 <div class="form-group">
							<label for="contracttime"  class="col-sm-2 control-label">合同签订时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="contracttime"   name="contracttime"   onclick="laydate()">
							</div>
						</div>
						
			<!-- 			 		 <div class="form-group">
							<label for="lastbacktime"  class="col-sm-2 control-label">最迟还款时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="lastbacktime"   name="lastbacktime"  onclick="laydate()">
							</div>
						</div> -->
					    	
					    	 		 <div class="form-group">
							<label for=”begintime“ class="col-sm-2 control-label">起息时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="begintime"   name="begintime" onclick="laydate()" >
							</div>
						</div>
						
						
								 <div class="form-group">
							<label for=”firsttime“ class="col-sm-2 control-label">第一次还款:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="firsttime"   name="firsttime" onclick="laydate()" >
							</div>
						</div>
 
                  <div class="form-group" style="margin-top:10px">
							<label for="apr" class="col-sm-2 control-label">借款利息(年化):</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="apr"  name="apr"  onkeyup="value=value.replace(/[^\d.]/g,'')" maxlength="5"  size="14" placeholder="请输入小数 比如0.12 0.14">
							</div>
						</div>
				 
						
								
								    <div class="form-group" style="margin-top:10px">
								    	<label for="longtime" class="col-sm-2 control-label">借款期限:</label>
											<div class="input-group col-sm-6" style="padding-right: 15px;  padding-left: 15px;">
											  <input type="text" class="form-control" id="longtime"  name="longtime"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="2"  size="14">
											    <span class="input-group-addon"  >月</span>
			                                </div>  
						       </div>
						       
					    	
						
								 <div class="form-group">
							<label for="memo" class="col-sm-2 control-label">备注信息:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="memo"  name="memo" maxlength="400">  </textarea>
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
    	url:   "manager/addInvestAction.html"  ,
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
    
 
	if( $("#name").val()=="" ){ 
	     	warn(" 项目名称还没有填！","name",10,0)
        return false; 
    } 
	
	if( $("#investsid").val()=="" ){ 
     	warn(" 项目编号还没有填！","investsid",10,0)
    return false; 
} 
	
	if( $("#rongziid").val()=="0" ){ 
     	warn(" 融资客户还没选！","rongziid",10,0)
    return false; 
} 
	
	if( $("#rzmoney").val()=="" ){ 
     	warn(" 融资金额还没填！","rzmoney",10,0)
    return false; 
} 
	
	if( $("#rzbankid").val()=="0" ){ 
     	warn(" 还款账号还没选！","rzbankid",10,0)
    return false; 
} 
	

	
	if( $("#contracttime").val()=="" ){ 
     	warn(" 合同签署时间还没填！","contracttime",10,0)
    return false; 
} 
	
	if( $("#begintime").val()=="" ){ 
     	warn(" 起息时间还没填！","begintime",10,0)
    return false; 
} 
	
	if( $("#firsttime").val()=="" ){ 
     	warn(" 首次还款时间还没填！","firsttime",10,0)
    return false; 
} 
	
	if( $("#longtime").val()=="" ){ 
     	warn("投资期限还没填！","longtime",10,0)
    return false; 
} 
	if( $("#apr").val()=="" ){ 
     	warn("借款利息还没填！","apr",10,0)
    return false; 
} 
	if( $("#rzbankid").val()==0 ){ 
     	warn("借款人账户未填！","rzbankid",10,0)
    return false; 
} 
	
	
	
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="insertOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("新增成功！","name",100,0);
	     
	    	 }
	     else{
	    		warn("新增失败！","name",100,0);
	    	 
	     }
  
}  
  
  //根据融资客户 动态获取 银行下拉列表
  
  function rzIdChanged() 
 {
     if ( $("#rongziid").val()=="0")
    	 {
    	  $("#rzbankid").html("<option value='0'>请选择</option");
    	  
    	 } else
    		 {
    		 
    		  $.ajax({
   				type : 'POST',
   				url : '${pageContext.request.contextPath}/manager/getBanksByRongziId.html',
   				data : {"rongziId" : $("#rongziid").val()},
   			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
   				success : function(data) {
   					 var banks=data;
   					   //显示银行列表
   				     var rzbankid="";
   				    var length = banks.length;
   				    for(var i = 0; i < length; i ++){
   				    		rzbankid = rzbankid + "<option value="+banks[i].id+">"+banks[i].name+"</option>";
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
</html>
