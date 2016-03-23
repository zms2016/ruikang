<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
 <script src="components/layer2/layer/layer.js"></script>
 
</head>

<body>


	<div class="panel panel-primary"  style="margin-bottom: 0px;border: none" id="setrolepanel">
		<div class="panel-heading">设置角色的权限</div>
		<div class="panel-body">


			<div class="form-group" style="margin-top:10px">
				<label for="name" class="col-lg-2 control-label" style="padding:0">角色名称 :</label>
				<div class="col-lg-4">
					<input type="edit" class="form-control" id="name" name="name"   readonly value="${role.name }">
				</div>
			</div>

		    <!--    [{"rights":[{"id":3,"typeId":2,"name":"公告列表","description":"显示公告"},{"id":4,"typeId":2,"name":"公告优先级","description":"设置公告优先级"}],"type":{"id":2,"name":"公告管理","description":"管理公告相关","isUse":true}}] -->
		          
		     <div   id="rightcheckboxs"  style="margin-top:10px">
		                    <br>
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
 
 
 
		</div>

	</div>

 


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


 



</body>

<script>

var modules =new Array();

/*   把获取到的 角色拥有的大模块 */
function init() {

	
	  
	  modules="${rightIds}".split(",");
       
	    $("#setrolepanel :checkbox").each(function() {
	    	
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
 /*   获取所有选择框的选中状态，div的 id 后面和 :checkbox之间必须有空格！ */
/*   $("#setrolepanel :checkbox:checked").each(function(){  */
	
	  $(".rightschk:checked").each(function(){
       arr.push($(this).val()); 
  }); 
  
  var ids = arr.join(',');
  var roleid = '${role.id}';
  
 
	 
	    $.ajax({
			url : '${pageContext.request.contextPath}/manager/setRoleRightsAction.html',
		    type : "POST",
		    dataType:'json', 
		    data : {
		      "roleid" : roleid,
		      "ids" : ids
		    },
		    success : function(data) {
		     
		       layer.msg("设置成功！");
		       
		        layer.close(setRoleRightLay);
		        
		      //$("#xf_ht_fkzh_ttc2").hide();
		    },
		    error : function() {
		      alert("对不起，出现错误!");
		    }
		  });
	   
	   
 
 
  
}


$("#setbtncancel").click(function()
		{
	
	         layer.close(setRoleRightLay);
		});



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
