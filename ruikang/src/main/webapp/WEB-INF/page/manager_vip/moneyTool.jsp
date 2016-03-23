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

<title>理财计算器</title>

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
  <li><a href="javascript:void(0)">理财客户</a></li>
  <li class="active">理财计算器</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-7 ">
 
			<div class="panel panel-default">
				<div class="panel-heading">理财计算器</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					    <div class="form-group" style="margin-top:10px">
								    	<label for="memony" class="col-sm-2 control-label">合同金额:</label>
											<div class="input-group col-sm-6" style="padding-right: 15px;  padding-left: 15px;">
											 	<input type="text" class="form-control" id="memony"  name="memony"   onkeyup="value=value.replace(/[^\d.]/g,'')"  maxlength="7"  size="14" >
											    <span class="input-group-addon"  >元</span>
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
						
						 <div class="form-group" style="margin-top:10px">
							<label for="apr" class="col-sm-2 control-label">年收益率:</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="apr"  name="apr"  onkeyup="value=value.replace(/[^\d.]/g,'')" maxlength="5"  size="14" placeholder="请输入小数 比如0.12 0.14">
							</div>
						</div>
						
						
	 
						 <!-- 		 <div class="form-group">
							<label for="contracttime" class="col-sm-2 control-label">合同签订时间:</label>
							<div class="col-sm-6">
								<input type="text"  class="form-control"  id="contracttime"   name="contracttime"  onclick="laydate()"  >
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
								    	<label for="longtime" class="col-sm-2 control-label">投资期限:</label>
											<div class="input-group col-sm-6" style="padding-right: 15px;  padding-left: 15px;">
											  <input type="text" class="form-control" id="longtime"  name="longtime"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="2"  size="14">
											    <span class="input-group-addon"  >月</span>
			                                </div>  
						       </div>
					 
						
						
					 
	 
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" class="btn btn-primary  col-sm-2"  id="subBtn">开始模拟</button>
							</div>
						</div>
					</form>
				</div>
			</div>
 
		</div>
		    <!--   右边 -->
		    <div class="col-md-4">
		                <table class="table  table-striped table-bordered" style="margin-bottom:100px">
		                        <thead>
		                              <tr>
		                                  <td>序号</td>
		                                  <td>还款日期</td>
		                                  <td>还款金额</td>
		                              </tr>
		                        </thead>
		                         
		                         <tbody id="infoBody">
		                                 
		                         </tbody>
		             </table>
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
 
 Date.prototype.pattern=function(fmt) {         
	    var o = {         
	    "M+" : this.getMonth()+1, //月份         
	    "d+" : this.getDate(), //日         
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
	    "H+" : this.getHours(), //小时         
	    "m+" : this.getMinutes(), //分         
	    "s+" : this.getSeconds(), //秒         
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
	    "S" : this.getMilliseconds() //毫秒         
	    };         
	    var week = {         
	    "0" : "/u65e5",         
	    "1" : "/u4e00",         
	    "2" : "/u4e8c",         
	    "3" : "/u4e09",         
	    "4" : "/u56db",         
	    "5" : "/u4e94",         
	    "6" : "/u516d"        
	    };         
	    if(/(y+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
	    }         
	    if(/(E+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
	    }         
	    for(var k in o){         
	        if(new RegExp("("+ k +")").test(fmt)){         
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
	        }         
	    }         
	    return fmt;         
	}
 
 
 
 $("#subBtn").click(function(){
	 
	  if ( $("#memony").val()=="")
		  {
		   alert("请输入合同金额");
		  return false;
		  }
	  
	  
	  if ( $("#apr").val()=="")
	  {
	   alert("请输入年利率");
	  return false;
	  }
	  
	  if ( $("#begintime").val()=="")
	  {
	   alert("请输入起息时间");
	  return false;
	  }
	  
	  if ( $("#firsttime").val()=="")
	  {
	   alert("请输入第一次还款时间");
	  return false;
	  }
	  
	  if ( $("#longtime").val()=="")
	  {
	   alert("请输入投资期限");
	  return false;
	  }
	 
	  $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/manager/moneyToolAction.html',
				data : { 
					   memony:$("#memony").val(),
					   begintime:$("#begintime").val(),
					   firsttime:$("#firsttime").val(),
				     apr:$("#apr").val(),
				     longtime:$("#longtime").val(),
				     backtype:$("#backtype").val()
				 
				},
			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
				success : function(data) {
					     
					$("#infoBody").children("tr").remove();
					   var schedules=data.schedules;
					   
					    for (i=0;i<schedules.length;i++)
					    	{
					    	   $("#infoBody").append("<tr><td>"+(i+1) +"</td><td>"+schedules[i].timeStr +"</td><td>"+ schedules[i].money+          " </td><tr>")
					    	}
					    
					    $("#infoBody").append("<tr><td>总收益:</td><td colspan='2'>"+data.totalApr+"</td></tr>")
					
				},
				error : function() {
					alert("出错");
				}

			})
	 
	 
 });
 
    		 

    		 
 
 
 </script>
 
  
</html>
