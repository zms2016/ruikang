 
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

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

<title>恒金所理财业务经营系统</title>

   <!--   bootstrap 样式  默认需要-->
  <link rel="stylesheet" href="components/FontAwesome4.4/css/font-awesome.min.css">
  <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="components/cropper/dist/cropper.css">
 

     <!-- 首页左侧 折叠菜单 -->
 <link rel="stylesheet" href="css/main_leftmenu01.css">
 
   <!-- 设置头像弹出框 -->
  <link rel="stylesheet" href="css/cutUserFace.css">
  
  <link rel="stylesheet" href="css/custom.css">
   <!-- jquery和bootstrap 默认都需要 -->
<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 
  <script src="components/cropper/dist/cropper.js"></script>
  

     <!-- 首页左侧 折叠菜单 -->
     
<script src="js/main_leftmenu01.js"></script>

 <script src="js/jquery-form.js"></script> 
 <script src="components/layer2/layer/layer.js"></script>  
 
  <!-- 查看密码  -->
<script src="components/bootstrap-show-password/bootstrap-show-password.js"></script>

<script language="JavaScript"> 

 /* 跳出iframe */
   if (window != top) 
	   
	   {
         top.location.href = location.href; 
 
         //parent.location.reload();   
  
   }
 
 
 
 
</script>




  
  



<!-- <script src="components/layer2/layer/layer.js"></script> -->
 
<style type="text/css">


body {
	margin: 0;
	padding: 0;
	border: 0;
	height: 100%;
	max-height: 100%;
}

a {
	 color:#ffffff;
}

a:visited {
	  color:#ffffff;
	text-decoration: none;
}

a:active {
  color:#ffffff;
	text-decoration: none;
}
/* hover一定要放在最后面*/
a:hover {
	  color:#ffffff;
	text-decoration: none;
}



/*    主界面 top */
#toptitle {
	position: absolute;
	top: 0;
	left: 0;
	height: 100px;
	color: #ffffff;
	width: 100%;
	overflow: hidden;
	vertical-align: middle;
	background-color: #317eb4;
}


 /* 左边菜单 */
#leftmenu {
	position: fixed;
	top: 100px;
	left: 0;
	height: 100%;
	width: 180px;
	overflow: auto;
	vertical-align: top;
	background-color: #585858;
}

/*  右边内容iframe容器 */
#maincontent {
	position: fixed;
	left: 180px;
	top: 100px;
	right: 0;
	bottom: 0;
	  height: 100%;  
	overflow: auto;
	background: #fff;
	/* border-top: solid 1px #ea1718;
            border-left: solid 1px #ea1718;*/
}

 
</style>



<script type="text/javascript" language="javascript">

 

     //自动测算内容区域的高度
  	function iFrameHeight() {
  		
    	 var ifm = document.getElementById("content");
    	 
    	 var screenHight=window.screen.height;
    	 
    	 var ifmHight=screenHight-130;
    	 
    	 
    	 ifm.height=ifmHight;
    	 
/* 		var ifm = document.getElementById("content");
		var subWeb = document.frames ? document.frames["content"].document: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
		 
			
			ifm.height = subWeb.body.scrollHeight;
			
	 		if(ifm.height<940)
				{
				  ifm.height=940;
				}    
			
			//ifm.width = subWeb.body.scrollWidth; 这个不能用，用的话，每点一次左边的连接，右边内容宽度会增加一些
		} */
	} 
     
 
	
	
</script>

</head>
<body  id="mainbody">

 
	<div id="leftmenu">
	
	
		<div class="leftsidebar_box">
			
			<div class="line"></div>
			
			 <c:forEach var="superMenu"  items="${rightsList}">
			 
			    
			        <dl>
			            <dt style="background-image: url(${superMenu.tRightTypes.icopath})">
					          ${superMenu.tRightTypes.name }<img src="components/menu01/images/left/select_xl01.png">
				       </dt>
				   
				         <c:forEach var="secMenu"  items="${superMenu.rights }" >
			                 <dd>
			                        <a href="${secMenu.url }"  target="content">${secMenu.name }</a>
			                 </dd>
			           	
			           	</c:forEach>
			        
			        </dl>
			
			</c:forEach>
 
			
			
		</div>

	</div>


	<div id="toptitle">
		
		<%--  <div style="position: absolute;top: 10px;left: 20px;text-align: center">
       <img src="images/a8.jpg" style="width: 64px;height:64px;border-radius: 50%">
       <div><a href="javascript:void(0)"  id="openBtn">${username }</a></div>

         </div> --%>

		<div style="text-align: center;">

			<h1 style="color: #ffffff">恒金所理财业务经营系统</h1>
		</div>
		
		 <div  style="position: absolute;top: 10px;right: 20px;text-align: center">
		 	<div  style="float:right; ;font-size:18px;line-height:89px">
 
		      <a href="manager/loginout.html" style="margin-left:20px"> 退 出</a>
		</div>
		
		<div  style="float:right;margin-right:10px;font-size:18px">
		    <a href="javascript:void(0)"  id="openBtn">
		            <c:choose>
		               <c:when test="${ not empty user.pic }">
		                         <img src="/userFacePic/${user.pic }" style="width: 64px;height:64px;border-radius: 50%" id="userImage">
		               </c:when>
		                <c:otherwise>
		                         <img src="images/a8.jpg" style="width: 64px;height:64px;border-radius: 50%" id="userImage">
		                </c:otherwise>
		            
		            </c:choose>
		         </a>
		          <a href="javascript:void(0)"  id="changePwd">
                      <div style="font-size:12px"><c:out value="${user.realname } " /> </div>
                </a>
		</div>
		 
		 </div>
	
		
		

	</div>


	<div id="maincontent">

 	<iframe id="content" name="content"  onLoad="iFrameHeight()"  frameborder="0" scrolling="0" marginheight="0" marginwidth="0"   width="100%"  src="manager/defaultPage.html"></iframe>  
			 
	      <!--      刷新首页的时候 ，子页面路径保留 -->
		<%-- 	    <c:choose>
			    
			 
			           <c:when test="${ not empty nowUrl }">    
			             <iframe id="content" name="content"   height="99%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  width="100%"  src="${nowUrl}"></iframe>
			        </c:when>
			          <c:otherwise>
			             <iframe id="content" name="content"   height="99%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  width="100%"  src="manager/defaultPage.html"></iframe>
			          </c:otherwise>
			    </c:choose> --%>
			
			 

	</div>
	
 



<!-- Modal -->
<div class="modal fade" id="cutPicDialog" >
  <div class="modal-dialog" role="document" style="width: 1280px;">

    <div class="modal-content">

      <div class="modal-header"  style="background-color: #A3A4A5; color: #ffffff;  font-size: 20px;">
             头像裁剪
      </div>


      <div class="modal-body">



        <div class="container" style="margin-top: 40px; "   >

          <form  id="userFaceForm"   enctype="multipart/form-data">

                  <input type="hidden" id="userId"  name="userId" value="${user.id }">

            <div class="row">


              <div class="col-md-8"style="border: 1px solid #aaaaaa;border-radius: 4px;padding: 0px" >

                <div class="img-container" >

                  <img id="image" src="images/pic_default.png" alt="选择图片">
                </div>
              </div>

              <div class="col-md-3" style="border: 1px solid #aaaaaa;border-radius: 4px;margin-left: 30px">

                <div class="docs-preview clearfix">
                  <div class="img-preview preview-lg"></div>
                  <div class="img-preview preview-md"></div>
                  <div class="img-preview preview-sm"></div>
                  <div class="img-preview preview-xs"></div>
                </div>

                <!-- <h3 class="page-header">Data:</h3> -->
                <div class="docs-data">
                  <div class="input-group input-group-sm">
                    <label class="input-group-addon" for="dataX">左X</label>
                    <input type="text" class="form-control" id="dataX"   name="dataX" placeholder="x">
                    <span class="input-group-addon">px</span>
                  </div>
                  <div class="input-group input-group-sm">
                    <label class="input-group-addon" for="dataY">左Y</label>
                    <input type="text" class="form-control" id="dataY"     name="dataY"   placeholder="y">
                    <span class="input-group-addon">px</span>
                  </div>
                  <div class="input-group input-group-sm">
                    <label class="input-group-addon" for="dataWidth">宽度</label>
                    <input type="text" class="form-control" id="dataWidth"   name="dataWidth"  placeholder="width">
                    <span class="input-group-addon">px</span>
                  </div>
                  <div class="input-group input-group-sm">
                    <label class="input-group-addon" for="dataHeight">高度</label>
                    <input type="text" class="form-control" id="dataHeight"        name="dataHeight"         placeholder="height">
                    <span class="input-group-addon">px</span>
                  </div>
                  <!--  <div class="input-group input-group-sm">
                      <label class="input-group-addon" for="dataRotate">旋转</label>
                      <input type="text" class="form-control" id="dataRotate" placeholder="rotate">
                      <span class="input-group-addon">deg</span>
                    </div>-->

                </div>


                <!--开始 选择  缩放 提交 按钮-->
                <div class=" docs-buttons" style="margin-top: 20px">

                  <div class="btn-group" style="float: left">

                    <label class="btn btn-danger btn-upload " for="inputImage" title="选择图片" style="width:100px">
                      <input type="file" class="sr-only"    id="inputImage"   name="inputImage"    accept="image/*"   >
                       <span class="docs-tooltip" data-toggle="tooltip" title="选择图片">
                          <span class="fa fa-file-photo-o "></span>&nbsp;选图片 </span>
                    </label>
                  </div>


                  <div class="btn-group" style="float: left; ">

                    <button type="button" class="btn btn-primary" data-method="zoom" data-option="0.1" title="Zoom In"  style="    width: 48px;  ">
            <span class="docs-tooltip" data-toggle="tooltip" title="放大图片">
              <span class="fa fa-search-plus"></span>
            </span>
                    </button>

                    <button type="button" class="btn btn-primary" data-method="zoom" data-option="-0.1" title="Zoom Out"  style="    width: 48px; margin-left: 1px;">
            <span class="docs-tooltip" data-toggle="tooltip" title="缩小图片">
              <span class="fa fa-search-minus"></span>
            </span>
                    </button>

                  </div>   <!-- 选择  缩放 提交 按钮 结束 -->
 
 
  <div style="clear: both"> </div>


                    <div class="btn-group" style="float: left">


                      <button type="button" class="btn btn-default" data-dismiss="modal"  style="    width: 100px;">

                        <span class="fa fa-close"></span>取消 </span>
                      </button>

                    </div>


                    <div class="btn-group" style="float: left">
                      <button type="submit" class="btn btn-primary"  style="  width: 100px;">

                        <span class="fa fa-check"></span>提交
                        </span>
                      </button>


                    </div>
                    
                    
                   </div>   <!--  doc butons -->
                </div>
             


              </div>


            </div>


          </form>



        </div>

        <!-- 莫泰框boty大结束-->
     </div>

   <!--    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary"  id="subBtn">提交</button>
      </div> -->


  </div>
 
</div>
 
<script>

    $(function () {
      'use strict';
      var console = window.console || { log: function () {} };
      var $image = $('#image');

      var $dataX = $('#dataX');
      var $dataY = $('#dataY');
      var $dataHeight = $('#dataHeight');
      var $dataWidth = $('#dataWidth');
      var $dataRotate = $('#dataRotate');
      var $dataScaleX = $('#dataScaleX');
      var $dataScaleY = $('#dataScaleY');
      var options = {
         //选框区域  1:1
        aspectRatio: 1 / 1,

         //预览区域
        preview: '.img-preview',
        crop: function (e) {
          $dataX.val(Math.round(e.x));
          $dataY.val(Math.round(e.y));
          $dataHeight.val(Math.round(e.height));
          $dataWidth.val(Math.round(e.width));
          $dataRotate.val(e.rotate);
          $dataScaleX.val(e.scaleX);
          $dataScaleY.val(e.scaleY);
        }
      };



      // Tooltip
      $('[data-toggle="tooltip"]').tooltip();


      // 响应 选框 事件
      $image.on({
        'build.cropper': function (e) {
          console.log(e.type);
        },
        'built.cropper': function (e) {
          console.log(e.type);
        },
        'cropstart.cropper': function (e) {
          console.log(e.type, e.action);
        },
        'cropmove.cropper': function (e) {
          console.log(e.type, e.action);
        },
        'cropend.cropper': function (e) {
          console.log(e.type, e.action);
        },
        'crop.cropper': function (e) {
          console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
        },
        'zoom.cropper': function (e) {
          console.log(e.type, e.ratio);
        }
      }).cropper(options);









      // 放大 缩小事件  2个按钮上 增加了 data-method="zoom" 属性
      $('.docs-buttons').on('click', '[data-method]', function () {
        var $this = $(this);
        var data = $this.data();
        var $target;
        var result;

        if ($this.prop('disabled') || $this.hasClass('disabled')) {
          return;
        }

        if ($image.data('cropper') && data.method) {
          data = $.extend({}, data); // Clone a new one

          if (typeof data.target !== 'undefined') {
            $target = $(data.target);

            if (typeof data.option === 'undefined') {
              try {
                data.option = JSON.parse($target.val());
              } catch (e) {
                console.log(e.message);
              }
            }
          }

          result = $image.cropper(data.method, data.option, data.secondOption);

          switch (data.method) {
            case 'scaleX':
            case 'scaleY':
              $(this).data('option', -data.option);
              break;

            case 'getCroppedCanvas':
              if (result) {

                // Bootstrap's Modal
                $('#getCroppedCanvasModal').modal().find('.modal-body').html(result);

                if (!$download.hasClass('disabled')) {
                  $download.attr('href', result.toDataURL());
                }
              }

              break;
          }

          if ($.isPlainObject(result) && $target) {
            try {
              $target.val(JSON.stringify(result));
            } catch (e) {
              console.log(e.message);
            }
          }

        }
      });


      // Keyboard
      $(document.body).on('keydown', function (e) {

        if (!$image.data('cropper') || this.scrollTop > 300) {
          return;
        }

        switch (e.which) {
          case 37:
            e.preventDefault();
            $image.cropper('move', -1, 0);
            break;

          case 38:
            e.preventDefault();
            $image.cropper('move', 0, -1);
            break;

          case 39:
            e.preventDefault();
            $image.cropper('move', 1, 0);
            break;

          case 40:
            e.preventDefault();
            $image.cropper('move', 0, 1);
            break;
        }

      });


      // 选择 图片 预览图片
      var $inputImage = $('#inputImage');
      var URL = window.URL || window.webkitURL;
      var blobURL;

      if (URL) {



        $inputImage.change(function () {

          var files = this.files;
          var file;

          if (!$image.data('cropper')) {
            return;
          }

          if (files && files.length) {
            file = files[0];

            if (/^image\/\w+$/.test(file.type)) {
              blobURL = URL.createObjectURL(file);
              $image.one('built.cropper', function () {

                // Revoke when load complete
                URL.revokeObjectURL(blobURL);
              }).cropper('reset').cropper('replace', blobURL);
              //$inputImage.val(''); 这句话一定要注释掉， 不知道官方为什么要把input制空，导致 后台收不到多媒体文件
            } else {
              window.alert('请选择一张图片.');
            }
          }
        });
      } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
      }

  
     
        
 
        
        


    });



  </script>


</div>

 
<!-- 结束模态框 -->





  <!--修改密码-->
<div class="modal fade"  id="pwdModel">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><strong>修改密码</strong></h4>
            </div>

            <div class="modal-body" style="color: #656464">


                <form  id="changePwdForm">
                
                    <input type="hidden" id="id" name="id" value="${user.id }">
                    <div class="form-group">
                        <label for="password">输入新密码( 英文字母开头，只含有英文字母、数字和下划线)</label>
                        <input type="password" class="form-control" id="password"  name="password"  maxlength="20" onkeyup="value=value.replace(/[^A-Za-z0-9_-]*$/g,'')"   data-toggle="password"  data-placement="before">
                    </div>
                    <div class="form-group">
                        <label for="rePwd">确认密码</label>
                        <input type="password" class="form-control" id="rePwd" name="rePwd" maxlength="20" onkeyup="value=value.replace(/[^A-Za-z0-9_-]*$/g,'')"  data-toggle="password"  data-placement="before">
                    </div>

                    <button type="button" class="btn btn-default"  id="changPwdBtn">确定</button>
                </form>



            </div>



        </div><!-- /.modal-content -->

    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
 <!-- 修改密码结束-->
 
 
 
</body>

 
 

  
  <script>
  
  
  
  $("#openBtn").click(function()
		  {

		      $('#cutPicDialog').modal({
		          keyboard:false, //键盘 ESC不关闭
		          backdrop:'static' //点击空白区 不关闭
		    })


		  });
  

  $("#changePwd").click(function() {

      $('#pwdModel').modal({
          keyboard: false,  //按esc  不关闭
          backdrop: 'static' //点空白处 不关闭
      })
      
      
      $("#password").val("");
      $("#rePwd").val("");

  });




$("#changPwdBtn").click(function(){

    if (  $("#password").val()=="")
    {
        alert("请输入密码");
        return false;
    }
   if (  $("#rePwd").val()=="")
   {
       alert("请确认密码");
       return false;
   }

   if (  $("#password").val() != $("#rePwd").val())
   {
       alert("两次输入的密码不一致");
       return false;
   }
   
   
    
   var aj = $.ajax( {  
	     url:'${pageContext.request.contextPath}/manager/changeUserPwd.html', 
	      data:{  
	    	  "userid" : ${user.id},
	    	  "password":$("#password").val()
	 
	       },  
	      type:'post',  
	     // cache:false,  
	       dataType : 'json', //要求服务器返回 json对象 而不是字符串
	     success:function(data) {  
	       
	    	 
	    	  if (data.msg=="updateOK")
	    		  {
	    		   layer.msg("修改成功！")
	    		   $("#pwdModel").modal('hide');
	    		  }
	    	  else{
	    		  
	    		  layer.msg("修改失败！");  
	    	  }
 
	       },  
	     error : function() {  
	            
	             layer.msg("服务器异常！");  
	    }  
	   });
   


});
  
  
  </script>
  
  
   <script>
 
 

$(function(){ 
	
	
    var options = {  
    	url:   "manager/setUserFacePic.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: false,    //把表单清空 ，由于有图片预览，需要在 showResponse 里单独处理
        dataType:  "json",
   
    };  
  
    $("#userFaceForm").submit(function() {  
          
    	    $(this).ajaxSubmit(options);  
    	    // 阻止默认的提交事件
    	    return false;
    	    
    });  
}); 
 
 //提交前验证
function showRequest(formData, jqForm, options) {  
    
   if ($("#dataX").val()=="")
	   {
	   
	   return false;
	   }
	  
   if ($("#dataX").val()=="")
   {
   
   return false;
   }
   
   if ($("#dataY").val()=="")
   {
   
   return false;
   }
   
   if ($("#dataWidth").val()=="")
   {
   
   return false;
   }
   
   if ($("#dataHeigh").val()=="")
   {
   
   return false;
   }
   
 
   
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	   
	  
	        if (responseText.code==1)
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		 
	    	     
	    	     $("#dataX").val("");
	    	     $("#dataY").val("");
	    	     $("#dataWidth").val("");
	    	     $("#dataHeigh").val("");
	    	     
	    	     $("#cutPicDialog").modal('hide');
	    	     layer.msg("头像设置成功");
	    	     
	    	     $("#userImage").attr("src","/userFacePic/"+responseText.msg);
	    	    
	    	 
	    	 } else
	    		 {
	    		  layer.msg("头像设置失败");
	    		 
	    		 }
	  
  
}  

</script>



</html>