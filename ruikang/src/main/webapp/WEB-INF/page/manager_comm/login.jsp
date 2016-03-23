<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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


<script type="text/javascript">

        

      if(  window.navigator.userAgent.indexOf("Chrome") >0  | window.navigator.userAgent.indexOf("Firefox")>0 |  window.navigator.userAgent.indexOf("Safari")>0 )
    	  {
    	  
    	  }
      else
    	  {
    	  
    	     alert("不好意思，您使用的浏览器不支持HTML5! \n  建议使用 chrome 或者 firefox! \n如果您铁了心要用IE，请升级到版本11");
    	     
    	  }
 
   
   
</script>


<meta charset="UTF-8">

<title>
恒金所理财业务经营系统
</title>
<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>

  <!-- 查看密码  -->
<script src="components/bootstrap-show-password/bootstrap-show-password.js"></script>


<style type="text/css">
html,body {
	height: 100%;
}

 /*大背景 使用了过度色*/
.box {
 	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#5AAFEA', endColorstr='#133A58'); /*  IE */
	background-image:linear-gradient(bottom, #5AAFEA 0%, #133A58 100%);
	background-image:-o-linear-gradient(bottom, #5AAFEA 0%, #133A58 100%);
	background-image:-moz-linear-gradient(bottom, #5AAFEA 0%, #133A58 100%);
	background-image:-webkit-linear-gradient(bottom, #5AAFEA  0%, #133A58  100%);
	background-image:-ms-linear-gradient(bottom, #5AAFEA 0%, #133A58 100%);
	
	
	margin: 0 auto;
	position: relative;
	width: 100%;
	height: 100%;
}

.login-box {
	width: 100%;
	max-width:500px;
	height: 400px;
	position: absolute;
	top: 40%;

	margin-top: -200px;
	/*设置负值，为要定位子盒子的一半高度*/
	
}
@media screen and (min-width:500px){
	.login-box {
		left: 50%;
		/*设置负值，为要定位子盒子的一半宽度*/
		margin-left: -250px;
	}
}	

.form {
	width: 100%;
	max-width:500px;
	height: 275px;
	margin: 25px auto 0px auto;
	padding-top: 25px;
}	
.login-content {
	height: 340px;
	width: 100%;
	max-width:500px;
	background-color: rgba(255, 250, 2550, 0.6);
	float: left;
}		
	
	
.input-group {
	margin: 0px 0px 30px 0px !important;
}
.form-control,
.input-group {
	height: 40px;
}

.form-group {
	margin-bottom: 0px !important;
}
.login-title {
	padding: 20px 10px;
	background-color: #215D88;
}
.login-title h1 {
	margin-top: 10px !important;
}
.login-title small {
	color: #fff;
}

.link p {
	line-height: 20px;
	margin-top: 30px;
}
.btn-sm {
	padding: 8px 24px !important;
	font-size: 16px !important;
}
</style>



</head>


<script language="JavaScript"> 

 /* 跳出iframe */
   if (window != top) 
         top.location.href = location.href; 
 
 
 
  // 响应回车键  页面绑定onkeydown事件

   document.onkeydown=keyListener;
  
   function keyListener(e){
	   
   	e = e ? e : event;// 兼容FF
   	
   	if(e.keyCode == 13){
   		$("#submitBtn").click();
   	}
   }
 

       
       
</script>




 
<body>
 

 <div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1><small>恒金所理财业务经营系统</small></h1>
			</div>
			<div class="login-content ">
			<div class="form">
			<form action="#" method="post">
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
							<input type="text" id="username" name="username" class="form-control" placeholder="用户名">
						</div>
					</div>
				</div>
				<div class="form-group">
				<div class="col-xs-12  ">
					<div class="input-group"  style="width:100%">
						<!-- <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span> -->
						<input type="password"  id="password" name="password" class="form-control" placeholder="密码"  data-toggle="password"  data-placement="before">
					</div>
				</div>
			</div>
			
			 
			 

	<!-- 			<div class="form-group">
					<div class="col-xs-8  " style="padding-right: 0">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-paperclip"></span></span>
							<input type="text"   class="form-control"  id="kaptcha" name="kaptcha" placeholder="请输入验证码">
						</div>
					</div>
					<div class="col-xs-4  " style="padding-left: 1px">
						<div class="input-group">

							<img src="captcha-image.html"     height="40"  width="90"    id="kaptchaImage"    onclick="changePic()"/>
						</div>
					</div>

				</div> -->


				<div class="form-group form-actions">
					<div class="col-xs-8 col-xs-offset-2 ">
						<button type="button" class="btn btn-lg" style="background-color: #3775A1;color:#ffffff;width: 100%"   id="submitBtn"><span class="glyphicon glyphicon-check"></span> 登&nbsp;录</button>
					</div>
				</div>
		 
			</form>
			</div>
		</div>
	</div>
</div>

 
	
    <script  >    
    
 
           	 function changePic()
           	 {
           		 
           		$("#kaptchaImage").attr('src', 'captcha-image.html?' + Math.floor(Math.random()*100) ).fadeIn(); 
           	 }
            
            
           	 $("#submitBtn").click(function(){
           		 
           		$.ajax({
        			type : 'POST',
        			url : '${pageContext.request.contextPath}/manager/doLogin.html',
        		    dataType:'json',  //要求服务器返回 json对象 而不是字符串
        			data : {
        				      "username" : $("#username").val(),
        				      "kaptcha": $("#kaptcha").val(),
        				      "password":$("#password").val()
        				      
        				       },
        			success : function(data) {

        				   
        			      if (data.msg=="loginOk")
        			    	  {
        			    	     window.location.href="${pageContext.request.contextPath}/manager/main.html"; 
        			    	     return;
        			    	  }
        			      else if (data.msg=="passwordError")
        			    	  {
        			    	  
        			    	   alert("密码不正确!");
        			    	   return;
        			   		  //changePic();
        			    	  }
        			      else if (data.msg=="userError")
    			    	  {
    			    	  
    			    	   alert("账号已停用!");
    			    	   return;
    			   		  //changePic();
    			    	  }
        			      
        			      else if (data.msg=="picError")
    			    	  {
        			    	 
    			    	   alert("验证码不正确!");
    			   		    changePic();
    			   		 return;
    			    	  }
        			      
        			},
        			error : function() {
        				alert("出错");
        				changePic();
        			}

        		})
           		 
           		 
           		 
           	 });
      
       </script> 

</body>
</html>
