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

<title>修改融资项目</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<!--     <link  rel="stylesheet" href="components/jqueryui/jquery-ui.min.css"> -->

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

    <!-- <script src="components/jqueryui/jquery-ui.min.js"></script> -->

<script src="components/laydate/laydate.js"></script>
<script src="js/jquery-form.js"></script>

<!--     用来将日期转为 字符串 -->
    <script src="js/zmsTime.js"></script>
 
 
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
  <li class="active">修改融资项目</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">编辑融资项目信息</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
						
						
						<input type="hidden"  id="id" name="id" value="${investVo.invest.id }">
						<%-- <input type="hidden"  id="htTime" name="htTime" value="${investVo.htTime }">
						<input type="hidden"  id="lastTime" name="lastTime" value="${investVo.lastTime }"> --%>
						
						
						<div class="form-group" style="margin-top:10px">
							<label for="name" class="col-sm-2 control-label">项目名称:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name"  name="name"   value="${investVo.invest.name}" maxlength="40" disabled="disabled">
							</div>
						</div>
						
						
						<div class="form-group" style="margin-top:10px">
							<label for="investsid" class="col-sm-2 control-label">项目编号:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="investsid"  name="investsid"  value="${investVo.invest.investsid}" maxlength="40" disabled="disabled">
							</div>
						</div>
						
						
						
						     
                         <div class="form-group">
							<label for="description" class="col-sm-2 control-label">融资客户:</label>
							<div class="col-sm-6">
								   <select  id="rongziid"   name="rongziid"  class="  form-control"  onchange="rzIdChanged()"   disabled="disabled">
                                                  
                                                 
							                         <c:forEach var="item" items="${rzs}"    varStatus="status">
							                         
							                              <c:choose>
							                                      <c:when test="${investVo.invest.rongziid==item.id}">
							                                          <option value="${item.id}"  selected="selected">${item.name}</option>
							                                       </c:when>
							                                       <c:otherwise>
							                                           <option value="${item.id}"  >${item.name}</option>
							                                       </c:otherwise>
							                              </c:choose>
							                      
							                         </c:forEach>     
							            </select>
							</div>
						</div>
						
						
						          <div class="form-group">
							
							<label for="rzmoney" class="col-sm-2 control-label">融资金额:</label>
								<div class="col-sm-6">
								
						      
									<input type="text" class="form-control" id="rzmoney"  name="rzmoney" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="8" size="14"  
									     value="${investVo.rzMoney}" disabled="disabled">
								</div>
						</div>
						
						
						
							          <div class="form-group">
							
							<label for="needmoney" class="col-sm-2 control-label">剩余金额:</label>
								<div class="col-sm-6">
								
						      
									<input type="text" class="form-control" id="needmoney"  name="needmoney"   onKeyPress="if (event.keyCode!=46 && event.keyCode!=45 && (event.keyCode<48 || event.keyCode>57)) event.returnValue=false" maxlength="8" size="14"  
									     value="${investVo.needMoney}">
								</div>
						</div>
						
						
						                 <div class="form-group">
						             <!--     '还款方式 0 先息后本 1 等额本息 2 一次性还款付息', -->
							<label for="backtype" class="col-sm-2 control-label">还款方式</label>
							<div class="col-sm-6">
								   <select  id="backtype"   name="backtype"  class="  form-control"    disabled="disabled">
                                                 <c:choose>
                                                     <c:when test="${investVo.invest.backtype==0 }">
                                                       <option value="0"  selected="selected">先息后本</option>
                                                  <option value="1">等额本息</option>
                                                  <option value="2">一次性还款付息</option>
                                                     </c:when>
                                                      <c:when test="${investVo.invest.backtype==1 }">
                                                        <option value="0">先息后本</option>
                                                  <option value="1" selected="selected">等额本息</option>
                                                  <option value="2">一次性还款付息</option>
                                                     
                                                     </c:when>
                                                      <c:when test="${investVo.invest.backtype==2}">
                                                       <option value="0">先息后本</option>
                                                  <option value="1">等额本息</option>
                                                  <option value="2" selected="selected">一次性还款付息</option>
                                                     </c:when>
                                                 </c:choose>
                                                
							                        
							            </select>
							</div>
						</div>
						
 
						 		 <div class="form-group">
							<label for=contracttime class="col-sm-2 control-label">合同签订时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="contracttime"   name="contracttime"  onclick="laydate()" value="${investVo.htTime }" disabled="disabled">
							</div>
						</div>
						
						 		 <div class="form-group">
							<label for=lastbacktime class="col-sm-2 control-label">最迟还款时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="lastbacktime"   name="lastbacktime"  onclick="laydate()"  value="${investVo.lastTime }">
							</div>
						</div>
					    	
					    	
						
								 <div class="form-group">
							<label for="memo" class="col-sm-2 control-label">备注信息:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="memo"  name="memo" maxlength="400">${investVo.invest.memo }</textarea>
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

 
<!--  <script>
 
 
 
 $(function() {

     $( "#contracttime" ).datepicker();
     $( "#contracttime" ).datepicker( "option", "dateFormat", "yy-mm-dd");
     
     $( "#lastbacktime" ).datepicker();
     $( "#lastbacktime" ).datepicker( "option", "dateFormat", "yy-mm-dd");
     
    
   
     
     $( "#contracttime" ).datepicker().datepicker('setDate',$("#htTime").val());
     $( "#lastbacktime" ).datepicker().datepicker('setDate',$("#lastTime").val());
     
 });
 
 
 </script> -->
 
 
 


<script>

  //融资客户 下拉列表 变动事件
/*  响应一级菜单 changed事件 */
function rzIdChanged( )
{
  
		  $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/manager/getBankByUserId.html',
				data : {"userId" : $("#rongziid").val()},
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


$(function(){ 
    var options = {  
    	url:   "manager/updateInvestAction.html"  ,
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
