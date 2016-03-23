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

<title>增加访谈记录</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

<script src="js/jquery-form.js"></script>
<!-- 不用他
<script src="components/layer2/layer/layer.js"></script> -->
<script src="js/toast.js"></script>  

<script src="components/laydate/laydate.js"></script>

<script src="js/zmsTools.js"></script>


 

</head>

<body>
       
       <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active">新增拜访记录</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">拜访记录录入</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
				 
			 
                         <div class="form-group">
							<label for="vipid" class="col-sm-2 control-label">理财客户:</label>
							<div class="col-sm-6">
								   <select  id="vipid"   name="vipid"  class="  form-control"   >
              
							              
							                         
							                       <c:forEach var="item" items="${vips}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>    
							            </select>
							</div>
						</div>
						 
						
						    
                   
						
						
						    <div class="form-group">
							<label for="visittype" class="col-sm-2 control-label">拜访类型:</label>
							<div class="col-sm-6">
								   <select  id="visittype"   name="visittype"  class="  form-control"   >
              
							                 <option value="1">当面拜访</option>
							                 <option value="2">电话回访</option>
							                 <option value="3">短信微信</option>
							                         
							                         
							            </select>
							</div>
						</div>
						
						
						 
						   <div
						   <div class="form-group">
							<label for="visittime" class="col-sm-2 control-label">拜访时间</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="visittime"   name="visittime"   onclick="laydate()"   >
							</div>
						</div>
						 
						 	
								 <div class="form-group">
							<label for="content" class="col-sm-2 control-label">访谈记录:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="content"  name="content" maxlength="2000"></textarea>
							</div>
						</div>
					    	
					    	
						
								 <div class="form-group">
							<label for="memo" class="col-sm-2 control-label">备注信息:</label>
							<div class="col-sm-6">
								<textarea    class="form-control" id="memo"  name="memo" maxlength="400"></textarea>
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
    	url:   "manager/addVipVisitAction.html"  ,
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
    
 
	if( $("#visittime").val()=="" ){ 
	     	warn(" 拜访时间还没有填！","visittime",10,0)
        return false; 
    } 
	
	if( $("#content").val()==""){ 
     	warn(" 拜访内容还没有填！","content",10,0)
    return false; 
} 
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="insertOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("新增成功！","vipid",100,0);
	     
	    	 }
	     else{
	    		warn("新增失败！","vipid",100,0);
	    	 
	     }
  
}  

</script>
</html>
