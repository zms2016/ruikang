<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
 
request.setCharacterEncoding("UTF-8");
 
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
	<title>内容新增</title>
	
	<base href="<%=basePath%>">
	
	     <!-- 文本编辑器 css -->
	<link rel="stylesheet" href="components/kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="components/kindeditor-4.1.10/plugins/code/prettify.css" />
	              
	           <!--    bootstrap -->
	    <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

        <script src="js/jquery.min.js"></script>
        <script src="components/bootstrap/js/bootstrap.min.js"></script>
        
        <script src="js/jquery-form.js"></script>
 
   <!--  layer弹出层， 一般用 layer.msg来弹出提示信息 -->
<!-- <script src="components/layer2/layer/layer.js"></script>   -->

    <!-- 自定义 toast 可以针对某个元素 弹 toast -->
  <script src="js/toast.js"></script> 


        
 <!-- 上传图片预览插件 -->
<script src="js/previewImage.js"></script>
 
     <!-- 文本编辑器 js -->
	<script charset="utf-8" src="components/kindeditor-4.1.10/kindeditor.js"></script>
	<script charset="utf-8" src="components/kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="components/kindeditor-4.1.10/plugins/code/prettify.js"></script>
	
	<script>
	
	 /*   文本编辑器初始化 */
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '${pageContext.request.contextPath}/components/kindeditor-4.1.10/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/manager/fileUpload.html',
				fileManagerJson : '${pageContext.request.contextPath}/manager/fileManager.html',
				allowFileManager : true,
				afterBlur: function () { this.sync(); }, //如果使用 ajax提交 form 这句必须加，否则后台娶不到文本编辑器的值
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['contentForm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['contentForm'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body style="margin:20px">
 
	<form   id="contentForm"  name="contentForm"    enctype="multipart/form-data">
		 
		  <div class="form-group form-inline">
		  <strong style="font-size:1.2em"  > 内容类别:</strong>
		  <select  id="parentSelect" onchange="parentSelected( )" name="parentSelect"  class="  form-control"  style="margin-left:10px">
              
          
                   <c:forEach var="item" items="${typeList}">
                   
                      <option value="${item.id}">${item.name}</option>
                      
                   </c:forEach>
            </select>
             
            <select    id="typeid"    name="typeid"  class=" form-control" >
               <option value=0>-请选择-</option>  
         
            </select>
            </div>
            
             <!--   <div style="margin-bottom:20px;"  class="row">
                    <strong style="font-size:1.2em">  </strong> <input type="text"  id="title"  name="title"   style="width:70%" >
               </div> -->
           
             <div class="form-group form-inline">
                 <label for="filetitle">  <strong style="font-size:1.2em"  >文章标题： </strong></label>
                   <input type="text"  class="form-control"   id="filetitle"  name="filetitle"  style="width:70%" >
             </div>
             
		 <textarea name="content" cols="100" rows="8" style="width:80%;height:400px;visibility:hidden;">
		
		 </textarea>
		<br />
		
		  			
    <div class="form-group form-inline">

         <div class="row">
               <div class="col-sm-2">
                   <label for="picFile" >可以为文章添加图片,在首页轮播:</label> <br>

                   <input type="file" class="form-control"    id="picFile"   name="picFile">

               </div>

             <div class="col-sm-2" style="    margin-left: 5px;">

                 <div><img id="ImgPr" width="120" height="120"     src="images/pic_null.png"  style="border: 1px solid #B9B1B1; margin: 5px;"/></div>
             </div>

         </div>
   </div>
		
		
		    <div class="form-group">
							<div class=" col-sm-10">
								<button type="submit" class="btn btn-primary  col-sm-4"  id="subBtn">提交</button>
							</div>
						</div>
						
						
	</form>
</body>
</html>


 <script>
 
  //初始化 第2个 下拉框
 parentSelected( );
 
     $(function () {
	   $("#picFile").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120 });
	   });
 
 
      /* 响应菜单联动 */
      function parentSelected( )
      {
    	  var op = $("#parentSelect option:selected");
    	  var parentId = op.val();
    	  
    	  $.ajax({
  			type : 'POST',
  			url : '${pageContext.request.contextPath}/manager/getContentTypesByParentId.html',
  			data : {"parentId" : parentId},
  		   dataType:'json',  //要求服务器返回 json对象 而不是字符串
  			success : function(arr) {
                   
  			 //   var arr=eval(data);
  			     
  			    var childList;
  			    var length = arr.length;
  			    for(var i = 0; i < length; i ++){
  			    	if(arr[i].id != 31)
  			      childList = childList + "<option value="+arr[i].id+">"+arr[i].name+"</option>";
  			    }
  			    $("#typeid").html(childList);
  				 
  			},
  			error : function() {
  				alert("出错");
  			}

  		})
    	 
    	  
      }
 </script>
 
 
 <script>

$(function(){ 
    var options = {  
    	url:   "manager/addContentAction.html"  ,
    	type: "post"  ,
    	beforeSubmit:  showRequest,  //提交前处理 
        success:  showResponse,  //处理完成 
        resetForm: true,    //把表单清空 ，由于有图片预览，需要在 showResponse 里单独处理
        dataType:  "json",
   
    };  
  
    $("#contentForm").submit(function() {  
          
    	    $(this).ajaxSubmit(options);  
    	    // 阻止默认的提交事件
    	    return false;
    	    
    });  
}); 
 
 //提交前验证
function showRequest(formData, jqForm, options) {  
    
 
	if( $("#filetitle").val()=="" ){ 
	     	warn(" 文章标题没有写！","filetitle",10,0)
        return false; 
    } 
	
  	if($("#parentSelect").val()==null){ 
     	warn("一级类别没有选！","parentSelect",10,0)
    return false; 
    }  
  	
 
  	if($("#typeid").val()==null){ 
     	warn("子类别没有选！","typeid",10,0)
    return false; 
   }  
  	
 
 
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     
	     if (responseText.msg=="insertOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("新增成功！","filetitle",100,100);
	    		KindEditor.instances[0].html(""); //0表示第一个KindEditor编辑器对象
	    	 $("#ImgPr").attr("src","images/pic_null.png");
	    	 }
	     else{
	    		warn("新增失败！","filetitle",100,100);
	    	 
	     }
  
}  

</script>


  
 