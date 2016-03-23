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
	<title>新增融资项目</title>
	
	<base href="<%=basePath%>">
	
	     <!-- 文本编辑器 css -->
	<link rel="stylesheet" href="components/kindeditor-4.1.10/themes/default/default.css" />
	<link rel="stylesheet" href="components/kindeditor-4.1.10/plugins/code/prettify.css" />
	              
	           <!--    bootstrap -->
	    <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

        <script src="js/jquery.min.js"></script>
        <script src="components/bootstrap/js/bootstrap.min.js"></script>
        
        
 <!-- 上传图片预览插件 -->
<script src="js/previewImage.js"></script>

<script src="components/layer2/layer/layer.js"></script>
 
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
 
	<form  id="contentForm" name="contentForm"   method="post"    action="manager/addFinancialAction.html"     >
		 
           <input type="hidden"  id="id" name="id" value="${financial.id }">
           
             <div class="form-group form-inline">
                 <label for="title">  <strong style="font-size:1.2em"  >项目名称： </strong></label>
                   <input type="text"  class="form-control"   id="title"  name="title"  style="width:70%"   value="${financial.title }">
             </div>
             
                <div class="form-group form-inline">
                 <label for="apr">  <strong style="font-size:1.2em"  >年化收益： </strong></label>
                   <input type="text"  class="form-control"   id="apr"  name="apr"  style="width:70%" value="${financial.apr }" >
             </div>
             
                <div class="form-group form-inline">
                 <label for="longtime">  <strong style="font-size:1.2em"  >投资期限： </strong></label>
                   <input type="text"  class="form-control"   id="longtime"  name="longtime"  style="width:70%"  value="${financial.longtime }">
             </div>
             
                <div class="form-group form-inline">
                 <label for="backtype">  <strong style="font-size:1.2em"  >还款方式： </strong></label>
                   <input type="text"  class="form-control"   id="backtype"  name="backtype"  style="width:70%"  value="${financial.backtype }" >
             </div>
             
		      <textarea name="content"   id="content" cols="100" rows="8" style="width:80%;height:400px;visibility:hidden;">
		           ${financial.content } 
		     </textarea>
		 
		  <a href="javascript:void(0)"  class="btn btn-danger  " style="margin-top:20px"  id="subBtn"> 提交修改</a>  
		
		  <!-- <input type="submit"  value="提交"> -->
		
	</form>
</body>
</html>


 <script>
 
 
 
 
 
  $("#subBtn").click(function(){
	  
	  $.ajax({
			type : 'POST',
			url : '${pageContext.request.contextPath}/manager/updateFinancialAction.html',
		   	data:$("#contentForm").serialize(),// 你的formid  
		  /* 	data : {
				     "title":$("#title").val(),
				     "apr":$("#apr").val(),
				     "longtime":$("#longtime").val(),
				     "backtype":$("#backtype").val(),
				     "content":$("#content").text()
		
			
				
			     },   */
		    dataType:'json',  //要求服务器返回 json对象 而不是字符串
			success : function(data) {
                 
				  if (data.msg=="updateOK")
					  {
					  
						layer.msg('修改成功');
						 
						
					 
					  }
			 
			},
			error : function() {
				alert("出错");
			}

		})
	  
  });
 
 
 </script>
 
 