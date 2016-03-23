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
    
       <title>表单 多个 Input  file提交</title>

    <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
    <style>
    
          body{

            Font-size:62.5%
        }

        .a_pic{
            position: relative;
            background: #aaaaaa;
            width: 100%;
            height: 100px;
            line-height: 100px;
            color: #fff;
            font-size: 4rem;
            display: block;
            text-align: center;
            text-decoration: none;
            margin-bottom: 10px;
            cursor: pointer;
        }

        .file{
            position: absolute;
            top: 0;
            left: 0;
            width: auto;
            height: auto;
            line-height: 0;
            opacity: 0;
        }

        .delBtn{

            width: 80%;
            margin:0 auto;
            height: 100px;
             font-size: 4rem;
        }


        #filesForm   div{
            margin-top: 40px;
        }
        #filesForm div:first-child
        {
            margin-top:20px;
        }


    </style>
    <script src="js/jquery.min.js"></script>

    <script src="components/bootstrap/js/bootstrap.min.js"></script>
    
    
        <script type="text/javascript">
        /**
         * 从 file 域获取 本地图片 url
         */
        function getFileUrl(sourceId) {
            var url;
            if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
                url = document.getElementById(sourceId).value;
            } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
                url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
            } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
                url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
            }
            return url;
        }

        /**
         * 将本地图片 显示到浏览器上
         */
        function preImg(sourceId, targetId) {
            var url = getFileUrl(sourceId);
            var imgPre = document.getElementById(targetId);
            imgPre.src = url;
        }
    </script>
    
    

  
 <script>
     var  i = 1;
     $(document).ready(function () {

         $("#addBtn").click(function () {
             var doms=  "<div class=\"row\" id=\"div_"+i+"\" >"+
                             "<div class=\"col-xs-6\" style=\"border-radius: 4px;border: 1px solid #aaaaaa\">"+
                                 "<a href=\"javascript:void(0)\" class=\"a_pic\"  >"+
                                    "<span class=\"fa fa-picture-o\"></span>选择照片"+
                                    "<input  id=\"file_"+i+"\"  type=\"file\" name=\"file\" class=\"file\" onchange=\"preImg(this.id,'pic_"+i+"');\" >"+
                                 "</a>"+
                             "<img id=\"pic_"+i+"\" width=\"100%\" height=\"100%\" src=\"images/pic_default.png\"> "+
                             "</div>"+
                         "<div class=\"col-xs-4 .col-xs-offset-2\">"+
                         "<input type=\"button\" value=\"删除\" class=\"delBtn\"  onclick=\"del("+i+")\" class=\"btn btn-default\">"+
                         "</div>"+
                        "</div>";

             $("#filesForm").prepend(doms) ;
             i = i + 1;
         });
     });

 </script>


</head>


<body>


 <div class="container" style="margin-top: 100px">

 
 
  <input type="button" id="addBtn" class="btn btn-primary"  value="增加一行" style="width:30%;height: 100px;float: left ;font-size: 4rem ">

 <input type="button" id="submitBtn" class="btn btn-success"  value="上传" style="width: 30%;height: 100px;float: right;font-size: 4rem ">

     <div style="clear: both"></div>
     
     

<form name="filesForm"  id="filesForm" action="formFilesAction.html" enctype="multipart/form-data" method="post" style="margin-top:50px">

      <input type="hidden"  id="msg" name="msg"  value="我是谁">


<!--     <div class="row">
        <div id="div_0" class="col-xs-12">
            <input type="file" name="files" style="float: left" class="file">
            <input type="button" value="删除" style="float: left" onclick="del(0)">
        </div>

    </div> -->




</form>


 </div>

<script>

    function del(id) {


        $("#div_"+id).remove();

    }
    
    
    $("#submitBtn").click(function(){

        $("#filesForm").submit();
    });
    
    

</script>

</body>
</html>