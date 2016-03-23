<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<title>用户列表</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 <script src="components/layer2/layer/layer.js"></script>
</head>

<body>



	<div class="panel panel-default"  style="margin-bottom: 0px;border: none" id="setrolepanel">
		<div class="panel-heading"><strong>设置用户的特定权限</strong></div>
		<div class="panel-body"  style="margin-bottom:100px">


			<!-- <div class="form-group" style="margin-top:10px">
				<strong   style="color:#6b6b6b">拥有的角色名称 :</strong>
				<div  style="margin-left:10px;color:#8e8e8e" >
					 这里放角色名称列表 
				</div>
			</div> -->

		    <!--    [{"rights":[{"id":3,"typeId":2,"name":"公告列表","description":"显示公告"},{"id":4,"typeId":2,"name":"公告优先级","description":"设置公告优先级"}],"type":{"id":2,"name":"公告管理","description":"管理公告相关","isUse":true}}] -->
		      <div   id="rightcheckboxs"  style="margin-top:1px">
		       <!-- <strong   style="color:#6b6b6b">可选权限列表:</strong> -->
		                   <div   id="rightcheckboxs"  style="margin-left:20px;margin-top:20px">
					             <c:forEach var="rightmap"  items="${rightMap}">
					               
					                  <input type="checkbox"   value="a${rightmap.type.id}"    onclick="checknode(this)">      ${rightmap.type.name }
					                
					                   <div style="border:1px  #DEDEDE dotted;padding:20px;margin-top:10px" > 
					                   <c:forEach var="rights"  items="${rightmap.rights}"  varStatus="index">
					                   
					                                  <input type="checkbox"   value="${rights.id}"   name="rightsid"  class="rightschk">  ${rights.name}
					                               
					                                   <c:if test="${index.count % 4==0 }">
										                        <br>
										                </c:if>
					                        
					                 
					                    </c:forEach>
					                </div>  
					              <br>
					       </c:forEach>
		            </div>
              </div>
 
 
                <!-- 结束 checkbox -->
                
                 <div style="float:left;margin-left: 40px">
			<button type="button" class="btn btn-default"  onclick="selectAll()">全 选</button>
		</div>
		
		<div style="float:left;margin-left: 40px">
			<button type="button" class="btn btn-default " onclick="unSelectAll()">反 选</button>
		</div>
		
		<div style="float:left;margin-left: 40px">
			<button type="button" class="btn btn-primary " id="setbtnok" onclick="setGroupRights()">提交</button>
		</div>
	</div>
	
	
		</div>

	</div>

 


 
 


 



</body>

<script>


var modules =new Array();

/*   把获取到的 角色拥有的大模块 */
function init() {

	  
	  
	  modules="${rightIds}".split(",");
       
	 
	    $(".rightschk:checkbox").each(function() {
	    	
	      if ($.inArray($(this).val(),modules)>-1) {
	    	  
	        $(this).attr("checked","checked");
	      }
	    });
	  }

//初始化 选择框 该打勾的打勾
init();  


	
//设置权限
function setGroupRights() {
	
  var arr = new Array(); 
 /*   获取rightcheckboxs div下的所有选择框的选中状态，div的 id 后面和 :checkbox之间必须有空格！ */
/*    $("#rightcheckboxs :checkbox").each(function(){ */
	
	//获取 class为rightschk 所有打勾的chekckbox
  $(".rightschk:checked").each(function(){
 
       arr.push($(this).val()); 
  }); 
  
  var ids = arr.join(',');
 
   
	    $.ajax({
			url : '${pageContext.request.contextPath}/manager/setUserRightsAction.html',
		    type : "POST",
		    dataType:'json', 
		    data : {
		    
		      "ids" : ids,
		      "userId": ${userId}
		    },
		    success : function(data) {
		           if (data.msg=="insertOK")
		        	   {
		        	     layer.msg("设置成功！")
		        	   }
		           else
		        	   {
		        	   layer.msg("设置失败！")
		        	   }
		    },
		    error : function() {
		      alert("对不起，出现错误!");
		    }
		  });
	   
	   
 
 
  
}
    
  //全选所有checkbox
  function selectAll(){
	  
	/*   $("#rightcheckboxs :checkbox").attr("checked",true);    不要用 attr 这个玩意 全选可以，全不选，然后再全选就没用了。 貌似jquery1.7.2之后就可以用prop搞定    */
	  $("#rightcheckboxs :checkbox").prop("checked",true);      
	  
  }

  //反选所有checkbox
  function unSelectAll(){
	  
	  $("#rightcheckboxs :checkbox").prop("checked",false);       
	  
  }
  
  //响应大栏目 的 checkbox   
  function checknode(obj)
  {
	  
	  //jsp页面里  大栏目的checkbox 后面是一个div， 所有checkbox都在这个DIV里 所以先用next（） 取 DIV， 然后再DIV里查找所有 input （因为div下面只有 input checkbox)
	  $(obj).next().find("input").prop('checked',$(obj).is(":checked"));
  }
  
  
</script>


</html>
