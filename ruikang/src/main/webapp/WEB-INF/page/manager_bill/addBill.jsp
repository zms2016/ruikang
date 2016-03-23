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

<title>理财单据录入</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

 

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

     <script src="components/laydate/laydate.js"></script>

<script src="js/jquery-form.js"></script>
<script src="js/zmsTools.js"></script>
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
  <li class="active">理财单据录入</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">理财单据录入</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					 <input type="hidden" id="billFlag" name="billFlag" value="${billFlag }">
					
					<div class="form-group">
							<label for="contractid" class="col-sm-2 control-label">合同编号:</label>
							<div class="col-sm-6">
								   <select  id="contractid"   name="contractid"  class="  form-control"      onChange="contractChange()">
            
                                                      <option value="0">请选择合同</option>
							                         <c:forEach var="item" items="${contracts}">
							                   
							                      <option value="${item.id}">${item.contractid}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
						
						
						<div class="form-group">
						             <!--     '还款方式 0 先息后本 1 等额本息 2 一次性还款付息', -->
							<label for="backtype" class="col-sm-2 control-label">还款方式</label>
							<div class="col-sm-6">
								   <select  id="backtype"   name="backtype"  class="  form-control"   >
            
                                                  <option value="0">先息后本</option>
                                                  <!-- <option value="1">等额本息</option> -->
                                                  <option value="2">一次性还款付息</option>
							                        
							            </select>
							</div>
						</div>
						
						
						             <div class="form-group">
							<label for="investid" class="col-sm-2 control-label">融资项目:</label>
							<div class="col-sm-6">
								   <select  id="investid"   name="investid"  class="  form-control"     onChange="investChange()" >
            
                                                      <option value="0">请选择融资项目</option>
							                         
							            </select>
							</div>
							
							<div class="col-sm-4">
								 <span id="nowTotal"  style="line-height: 34px; color: #aaaaaa;  "> </span>
							</div>
							
							
						</div>
						
						
						
			                  <div class="form-group">
							<label for="rzbankid" class="col-sm-2 control-label">收款账户:</label>
							<div class="col-sm-6">
								   <select  id="rzbankid"   name="rzbankid"  class="  form-control"   >
            
                                                 <option value="0" >请选择收款账户</option>
                                                 
                                                 
							                       
							            </select>
							</div>
						</div>  
						
						
						
						
						
						 		 <div class="form-group">
							<label for="contracttime" class="col-sm-2 control-label">合同签订时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="contracttime"   name="contracttime"  onclick="laydate()"  >
							</div>
						</div>
						
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
								    	<label for="memony" class="col-sm-2 control-label">合同金额:</label>
											<div class="input-group col-sm-6" style="padding-right: 15px;  padding-left: 15px;">
											 	<input type="text" class="form-control" id="memony"  name="memony"   onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength="7"  size="14" >
											    <span class="input-group-addon"  >元</span>
			                                </div>  
						       </div>
						       
						       
						
						     
                  <div class="form-group" style="margin-top:10px">
							<label for="apr" class="col-sm-2 control-label">年收益率:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="apr"  name="apr"  onkeyup="value=value.replace(/[^\d.]/g,'')" maxlength="5"  size="14" placeholder="请输入小数 比如0.12 0.14">
							</div>
						</div>
				 
						
								
								    <div class="form-group" style="margin-top:10px">
								    	<label for="longtime" class="col-sm-2 control-label">投资期限:</label>
											<div class="input-group col-sm-6" style="padding-right: 15px;  padding-left: 15px;">
											  <input type="text" class="form-control" id="longtime"  name="longtime"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="2"  size="14">
											    <span class="input-group-addon"  >月</span>
			                                </div>  
						       </div>
					 
						
						
					 
	 
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary  col-sm-2"  id="subBtn">提交单据</button>
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
 
 
 
 function contractChange( )
 {
     if ( $("#contractid").val()=="0")
    	 {
    	  $("#investid").html("<option value='0'>请选择</option");
    	  $("#rzbankid").html("<option value='0'>请选择</option");
    	 } else
    		 {
    		 
    		  $.ajax({
   				type : 'POST',
   				url : '${pageContext.request.contextPath}/manager/getDateByContractId.html',
   				data : {"contractid" : $("#contractid").val()},
   			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
   				success : function(data) {
   					 
   					 var  contract=data.contract;
   					 var banks=data.banks;
   					 var invests=data.invests;
   				 
   					   //显示银行列表
   				     var rzbankid="";
   				    var length = banks.length;
   				    for(var i = 0; i < length; i ++){
   				    		rzbankid = rzbankid + "<option value="+banks[i].id+">"+banks[i].name+"</option>";
   				    }
   				   $("#rzbankid").html(rzbankid);  
   				   
   			      $("#rzbankid").val(contract.rzbankid);
   				   
   				  //显示项目列表
 				     var investid="";
 				    var length = invests.length;
 				    for(var i = 0; i < length; i ++){
 				    	investid = investid + "<option value="+invests[i].id+">"+invests[i].name+"</option>";
 				    }
 				   $("#investid").html(investid);  
 				   
 				   
 				      
 				    //选中
 		
 				    $("#investid").val(contract.investid);  
 				    
 				   investChange();
   					 
   				},
   				error : function() {
   					alert("出错");
   				}

   			})
    		 
    		 }
 		
  
 }  
 
 </script>
 


 <script>
 
 
  //根据融资项目变动 显示 当前融资项目已经融资了多少钱 
 function investChange( )
 {
     if ( $("#investid").val()=="0")
    	 {
    	      $("#nowTotal").text("");
    	 } else
    		 {
    		 
    		  $.ajax({
   				type : 'POST',
   				url : '${pageContext.request.contextPath}/manager/getSumOfInvest.html',
   				data : {"investid" : $("#investid").val()},
   			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
   				success : function(data) {
   					   if (data.code==1)
   						   {
   						   
   						   $("#nowTotal").text(data.msg);
   						   }
   					  
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
    	url:   "manager/addBillAction.html"  ,
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
    
 
	if( $("#contractid").val()=="0" ){ 
	     	warn("合同编号还没填！","contractid",10,0)
        return false; 
    } 
	
	if( $("#contracttime").val()=="" ){ 
     	warn("合同签订时间没填！","contracttime",10,0)
    return false; 
} 
	
	if( $("#begintime").val()=="" ){ 
     	warn("起息时间没填！","begintime",10,0)
    return false; 
} 
	
	if( $("#firsttime").val()=="" ){ 
     	warn("第一次还款时间没填！","firsttime",10,0)
    return false; 
	}
     	
	
	if( $("#memony").val()=="" ){ 
     	warn("合同金额没填！","memony",10,0)
    return false; 
} 
	
	 
	
	 if(! checkfloat($("#memony").val()) )
	  {      
			warn(" 合同金额不是有效数量！","memony",10,0) 
			return false;
	  }
	
	if( $("#apr").val()=="" ){ 
     	warn(" 年收益率没填！","apr",10,0)
    return false; 
} 
	 if (! checkfloat($("#apr").val()) )
	  {
			warn(" 年收益率不是有效数量！","apr",10,0) 
			return false;
	  }
	
	if( $("#longtime").val()=="" ){ 
     	warn(" 投资期限没填！","longtime",10,0)
    return false; 
} 
	

	
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="insertOK")
	    	 {
	    	   //防止重复提交  把 金额设为空
	    	    $("#memony").val("");
	    	   
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("理财单据录入成功，并已生成还款计划表！","contractid",100,0);
	     
	    	 }
	     else if (responseText.msg=="timeError"){
	    	 
	    	 //   $("#firsttime").text("");
	    	    $("#firsttime").val("");
	    	 warn("第一次还款日期必须大于起息时间！","firsttime",100,0);
	     }
	     else{
	    		warn("理财单据录入失败！","contractid",100,0);
	    	 
	     }
  
}  

</script>
</html>
