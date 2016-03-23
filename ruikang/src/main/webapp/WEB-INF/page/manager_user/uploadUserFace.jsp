
<%@page import="org.w3c.dom.Document"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 
%>
 
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<base href="<%=basePath%>">


   <!--   bootstrap 样式  默认需要-->
<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
  <link  rel="stylesheet" href="components/FontAwesome4.4/css/font-awesome.css">
   <link  rel="stylesheet" href="components/Jcrop-master/css/jquery.Jcrop.css">

   <!-- jquery和bootstrap 默认都需要 -->
<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
   <script src="components/Jcrop-master/js/jquery.Jcrop.js"></script>
 
 <script src="js/jquery-form.js"></script> 
 <script src="js/toast.js"></script>  
<style>

         /*大容器*/
        .box{
          
            border-radius: 5px;
          
            margin: 0 auto;
            margin-top:10px;
            height: 100%;
            overflow: hidden;
            padding: 5px;
        }

           /*文件上传 控件用图片替代*/
        .a_pic{
            position: relative;
            background: #be0000;
            width: 97px;
            height: 28px;
            line-height: 28px;
            color: #fff;
            font-size: 14px;
            display: block;
            text-align: center;
            text-decoration: none;
            margin-bottom: 10px;
            cursor: pointer;
        }

        a.a_pic {
            color: #ffffff;
            text-decoration: none;
        }
         a.a_pic :hover {
             color: #ffffff;
             text-decoration: none;
         }
          /* 把文件上传控件隐藏*/
        .file{
            position: absolute;
            top: 0;
            left: 0;
            width: auto;
            height: auto;
            line-height: 0;
            /* 把文件上传控件隐藏*/
            opacity: 0;
        }

         /*120像素的 预览图外面的 div*/
        .preview_box
        {
            border: 1px solid #aaaaaa;
            width:120px;
            height:120px;
            overflow:hidden;
        }

        /* 50像素的 预览图外面的 div*/
        .preview_box2
        {
            border: 1px solid #aaaaaa;
            width:50px;
            height:50px;
            overflow:hidden;
            margin-top: 50px;
        }


    </style>


    <script type="text/javascript">
        //定义一个全局api，这样操作起来比较灵活
        var api = null;
        function changePic(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.readAsDataURL(input.files[0]);
                reader.onload = function (e) {
                    $('#sourcePic').removeAttr('src');
                    $('#sourcePic').attr('src', e.target.result);

                    $('#preview_pic').removeAttr('src');
                    $('#preview_pic').attr('src', e.target.result);

                    $('#preview_pic2').removeAttr('src');
                    $('#preview_pic2').attr('src', e.target.result);


                    api = $.Jcrop('#sourcePic', {
                        onChange:showPreview,
                        onSelect:showPreview,
                        aspectRatio:1
                    });
                };
                if (api != undefined) {
                    api.destroy();
                }

        /*        $('#picForm').on('change','input',function(e){
                          var x1 = $('#x1').val(),
                            x2 = $('#x2').val(),
                            y1 = $('#y1').val(),
                            y2 = $('#y2').val();
                    api.setSelect([x1,y1,x2,y2]);
                });*/



                //简单的事件处理程序，响应自onChange,onSelect事件，按照上面的Jcrop调用
                function showPreview(coords){
                    $("#x1").val(coords.x);
                    $("#y1").val(coords.y);
                    $("#x2").val(coords.x2);
                    $("#y2").val(coords.y2);
                    $("#w").val(coords.w);
                    $("#h").val(coords.h);

                    if(parseInt(coords.w) > 0){
                        //计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
                        var rx = $("#preview_box").width() / coords.w;
                        var ry = $("#preview_box").height() / coords.h;

                        var rx2 = $("#preview_box2").width() / coords.w;
                        var ry2 = $("#preview_box2").height() / coords.h;

                        //通过比例值控制图片的样式与显示
                        $("#preview_pic").css({
                            width:Math.round(rx * $("#sourcePic").width()) + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
                            height:Math.round(rx * $("#sourcePic").height()) + "px",	//预览图片高度为计算比例值与原图片高度的乘积
                            marginLeft:"-" + Math.round(rx * coords.x) + "px",
                            marginTop:"-" + Math.round(ry * coords.y) + "px"
                        });


                        $("#preview_pic2").css({
                            width:Math.round(rx2 * $("#sourcePic").width()) + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
                            height:Math.round(rx2 * $("#sourcePic").height()) + "px",	//预览图片高度为计算比例值与原图片高度的乘积
                            marginLeft:"-" + Math.round(rx2 * coords.x) + "px",
                            marginTop:"-" + Math.round(ry2 * coords.y) + "px"
                        });

                    }

                }

            }

        }
    </script>





</head>
<body>

<div  >

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">修改${user.realname }头像</h3>
    </div>
    <div class="panel-body">
        <form   enctype="multipart/form-data"    id="webForm">

               <input type="hidden"  id="id"  name="id" value="${user.id }">
            
            <div class="box" >

                <div  style="margin-top: 20px"   class="fileSelect">
                    <a href="javascript:void(0)" class="a_pic">
                        <span class="fa fa-picture-o"></span>选择照片
                        <input type="file"   id="imgFile"  name="imgFile" class="file" onchange="changePic(this)">
                    </a>
                    <span style="font-family: 'Microsoft YaHei';font-size: 12px;color: rgb(102,102,102)">只能选择 jpg,png</span>
               <!--      <div  class="inline-labels" >
                        <label>X1 <input type="text" size="4" id="x1" name="x1" /></label>
                        <label>Y1 <input type="text" size="4" id="y1" name="y1" /></label>
                        <label>X2 <input type="text" size="4" id="x2" name="x2" /></label>
                        <label>Y2 <input type="text" size="4" id="y2" name="y2" /></label>
                        <label>W <input type="text" size="4" id="w" name="w" /></label>
                        <label>H <input type="text" size="4" id="h" name="h" /></label>
                    </div> -->
    
                         <input type="hidden" size="4" id="x1" name="x1" />
                          <input type="hidden" size="4" id="y1" name="y1" />
                         <input type="hidden"  size="4" id="x2" name="x2" />
                          <input type="hidden"size="4" id="y2" name="y2" /> 
                         <input type="hidden"  size="4" id="w" name="w" /> 
                         <input type="hidden" size="4" id="h" name="h" /> 
                 


                </div>



                <div>

                    <div   style="float: left; width: 980px; min-height: 500px;  border: 1px solid #aaaaaa;  "  >
                        <img src="images/pic_default.png" id="sourcePic"   width="980px" height="500px"/>
                    </div>


                    <div style="float: left;margin-left: 30px; width:200px; border:1px solid #aaaaaa;padding:20px">

                        <div id="preview_box" class="preview_box"    >
                            <img id="preview_pic" src="images/pic_default.png" />
                        </div>

                        <div id="preview_box2" class="preview_box2"  >
                            <img id="preview_pic2" src="images/pic_default.png" />
                        </div>

                    <!--     <a href="javascript:void(0)" id="subBtn" class="btn btn-danger" style="margin-top: 50px;">确认</a> -->
                       <button type="submit" class="btn btn-primary  col-sm-8"  id="subBtn" style="margin-top:60px">提交</button>
                         
                    </div>
                    
                  
                    
                </div>



            </div>

        </form>
    </div>
</div>


</div>

 
 </body>
 
 
 
 <script>
 
 

$(function(){ 
	
	
    var options = {  
    	url:   "manager/uploadUserFaceAction.html"  ,
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
    
 
	  xe=api.getScaleFactor()
      alert(xe[0]+"y的缩放比率:"+xe[1])
	/* if( $("#username").val()=="" ){ 
	     	warn(" 登陆账号还没填！","username",10,0)
        return false; 
    }  */
 
    return false;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("头像设置成功！","subBtn",100,0);
	    	 
	    	 } 
	     else if (responseText.msg=="fileError" )
	      {
	    		warn("文件格式不允许！","subBtn",100,0);
	    	 
	     }
	     else if (responseText.msg=="noFile" )
	      {
	    		warn("未发现有多媒体文件！","subBtn",100,0);
	    	 
	     }
  
}  

</script>



 </html>

