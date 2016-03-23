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

<title>编辑合同</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="components/jqueryui/jquery-ui.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>
    <script src="components/jqueryui/jquery-ui.min.js"></script>

<script src="js/jquery-form.js"></script>

<script src="js/toast.js"></script>  


<!--  不用他
<script src="components/layer2/layer/layer.js"></script>  


 上传图片预览插件
<script src="js/previewImage.js"></script> -->


<!--  使用自动补全  需要下面额 css和 js -->
<style>
        .custom-combobox {
            position: relative;
            display: inline-block;
        }
        .custom-combobox-toggle {
            position: absolute;
            top: 0;
            bottom: 0;
            margin-left: -1px;
            padding: 0;
            /* 支持： IE7 */
            *height: 1.7em;
            *top: 0.1em;
        }
        .custom-combobox-input {
            margin: 0;
            padding: 0.3em;
        }
    </style>
    <script>
        (function( $ ) {
            $.widget( "custom.combobox", {
                _create: function() {
                    this.wrapper = $( "<span>" )
                            .addClass( "custom-combobox" )
                            .insertAfter( this.element );

                    this.element.hide();
                    this._createAutocomplete();
                    this._createShowAllButton();
                },

                _createAutocomplete: function() {
                    var selected = this.element.children( ":selected" ),
                            value = selected.val() ? selected.text() : "";

                    this.input = $( "<input>" )
                            .appendTo( this.wrapper )
                            .val( value )
                            .attr( "title", "" )
                            .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
                            .autocomplete({
                                delay: 0,
                                minLength: 0,
                                source: $.proxy( this, "_source" )
                            })
                            .tooltip({
                                tooltipClass: "ui-state-highlight"
                            });

                    this._on( this.input, {
                        autocompleteselect: function( event, ui ) {
                            ui.item.option.selected = true;
                            this._trigger( "select", event, {
                                item: ui.item.option
                            });
                        },

                        autocompletechange: "_removeIfInvalid"
                    });
                },

                _createShowAllButton: function() {
                    var input = this.input,
                            wasOpen = false;

                    $( "<a>" )
                            .attr( "tabIndex", -1 )
                            .attr( "title", "显示所有数据" )
                            .tooltip()
                            .appendTo( this.wrapper )
                            .button({
                                icons: {
                                    primary: "ui-icon-triangle-1-s"
                                },
                                text: false
                            })
                            .removeClass( "ui-corner-all" )
                            .addClass( "custom-combobox-toggle ui-corner-right" )
                            .mousedown(function() {
                                wasOpen = input.autocomplete( "widget" ).is( ":visible" );
                            })
                            .click(function() {
                                input.focus();

                                // 如果已经可见则关闭
                                if ( wasOpen ) {
                                    return;
                                }

                                // 传递空字符串作为搜索的值，显示所有的结果
                                input.autocomplete( "search", "" );
                            });
                },

                _source: function( request, response ) {
                    var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
                    response( this.element.children( "option" ).map(function() {
                        var text = $( this ).text();
                        if ( this.value && ( !request.term || matcher.test(text) ) )
                            return {
                                label: text,
                                value: text,
                                option: this
                            };
                    }) );
                },

                _removeIfInvalid: function( event, ui ) {

                    // 选择一项，不执行其他动作
                    if ( ui.item ) {
                        return;
                    }

                    // 搜索一个匹配（不区分大小写）
                    var value = this.input.val(),
                            valueLowerCase = value.toLowerCase(),
                            valid = false;
                    this.element.children( "option" ).each(function() {
                        if ( $( this ).text().toLowerCase() === valueLowerCase ) {
                            this.selected = valid = true;
                            return false;
                        }
                    });

                    // 找到一个匹配，不执行其他动作
                    if ( valid ) {
                        return;
                    }

                    // 移除无效的值
                    this.input
                            .val( "" )
                            .attr( "title", value + " 匹配失败" )
                            .tooltip( "open" );
                    this.element.val( "" );
                    this._delay(function() {
                        this.input.tooltip( "close" ).attr( "title", "" );
                    }, 2500 );
                    this.input.data( "ui-autocomplete" ).term = "";
                },

                _destroy: function() {
                    this.wrapper.remove();
                    this.element.show();
                }
            });
        })( jQuery );

        $(function() {
            $( "#vipid" ).combobox();

        });
    </script>
    
    
</head>

<body>
       
       <ol class="breadcrumb">
  <li><span class="glyphicon glyphicon-home"></span><a href="manager/defaultPage.html">&nbsp;&nbsp;首页</a></li>
  <li><a href="javascript:void(0)">项目管理</a></li>
  <li class="active">领取合同</li>
</ol>
   
	<div class="row" style="margin:5px">
		<div class="col-md-10  ">
 
			<div class="panel panel-default">
				<div class="panel-heading">领取合同</div>
				<div class="panel-body">

					<form class="form-horizontal"       id="webForm"   >
					
					   <input type="hidden" id="id"  name="id"  value="${contract.id }">
					   
					 
					     
					   
						<div class="form-group" style="margin-top:10px">
							<label for="contractid" class="col-sm-2 control-label">合同编号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control"   id="contractid"  name="contractid"   readonly    value="${contract.contractid }">
							</div>
							
						</div>
						
						
						     
                         <div class="form-group">
							<label for="investid" class="col-sm-2 control-label">融资项目:</label>
							<div class="col-sm-6">
								   <select  id="investid"   name="investid"  class="  form-control"    onChange="investChange()"  >
            
                                                      <option value="0">请选择</option>
							                         <c:forEach var="item" items="${invests}">
							                   
							                      <option value=${item.id}>${item.name}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
							
							<div class="col-sm-4">
								 <span id="nowTotal"  style="line-height: 34px; color: #aaaaaa;  "> </span>
							</div>
							
							
						</div>
						
 
			                  <div class="form-group">
							<label for="rzbankid" class="col-sm-2 control-label">收款账户:</label>
							<div class="col-sm-6">
								   <select  id="rzbankid"   name="rzbankid"  class="  form-control"   >
            
                                                 <option value="0">请选择收款账户</option>
                                                 <c:forEach var="bank"   items="${banks}">
							                   
							                      <option value="${bank.id}">${bank.name}</option>
							                      
							                   </c:forEach>  
                                                 
							                       
							            </select>
							</div>
						</div>  
						
						            <div class="form-group">
							<label for="managerid" class="col-sm-2 control-label">理财经理:</label>
							<div class="col-sm-6">
								   <select  id="managerid"   name="managerid"  class="  form-control"    >
            
                                                      <option value="0">请选择</option>
							                         <c:forEach var="manager" items="${managers}">
							                   
							                      <option value="${manager.id}">${manager.realname}</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
						
						             <!--   自动补全 要加 class  ui-widget -->
						            <div class="form-group ui-widget">
							<label for="vipid" class="col-sm-2 control-label">理财客户:</label>
							<div class="col-sm-6">
								   <select  id="vipid"   name="vipid"  class="  form-control"   >
            
                                                      <option value="0">请选择</option>
							                         <c:forEach var="vip" items="${vips}">
							                   
							                      <option value="${vip.id }">${vip.name}-${vip.mobilenum }</option>
							                      
							                   </c:forEach>     
							            </select>
							</div>
						</div>
						
						
						
 
                          <div class="form-group">
							
							<label for="maybememony" class="col-sm-2 control-label">预计金额:</label>
								<div class="col-sm-6">
									<input type="maybememony" class="form-control" id="maybememony"  name="maybememony" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="7" size="14" >
								</div>
						</div>
						
						    <div class="form-group">
							
							<label for="memo" class="col-sm-2 control-label">备注说明:</label>
								<div class="col-sm-6">
									 <textarea    class="form-control" id="memo"  name="memo" maxlength="400">  </textarea>
								</div>
						</div>
						
						
					 
	 
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary  col-sm-2"  id="subBtn">确 定</button>
							</div>
						</div>
					</form>
				</div>
			</div>
 
		</div>
	</div>


</body>

 
 
 <script>
 
 
  //根据融资项目变动 显示 当前融资项目已经融资了多少钱 
 function investChange( )
 {
     if ( $("#investid").val()=="0")
    	 {
    	      $("#nowTotal").text("");
    	 } else
    		 {
    		 
    		  $.ajax({
   				type : 'POST',
   				url : '${pageContext.request.contextPath}/manager/getSumOfInvest.html',
   				data : {"investid" : $("#investid").val()},
   			   dataType:'json',  //要求服务器返回 json对象 而不是字符串
   				success : function(data) {
   					   if (data.code==1)
   						   {
   						   
   						   $("#nowTotal").text(data.msg);
   						   }
   					  
   				},
   				error : function() {
   					alert("出错");
   				}

   			})
    		 
    		 }
 		
  
 }  
 
 </script>
 
 

<script>

$(function(){ 
    var options = {  
    	url:   "manager/receiveContractAction.html"  ,
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
    
 
	 
	
	if( $("#investid").val()=="0" ){ 
     	warn(" 融资项目还没选！","investid",10,0)
    return false; 
} 
	
	 
	
	if( $("#rzbankid").val()=="0" ){ 
     	warn(" 收款账户没有填！","rzbankid",10,0)
    return false; 
} 
	
	if( $("#managerid").val()=="0" ){ 
     	warn(" 理财经理没有填！","managerid",10,0)
    return false; 
} 
	
	
	if( $("#vipid").val()=="0" ){ 
     	warn(" 理财客户没有填！","managerid",10,0)
    return false; 
} 
	
	if( $("#maybememony").val()=="" ){ 
     	warn(" 合同预计金额没有填！","maybememony",10,0)
    return false; 
} 
	
	
    return true;  
}  
  //获取反馈信息
function showResponse(responseText, statusText)  {  
	  
	     if (responseText.msg=="updateOK")
	    	 {
	    	     //使用自定义的弹窗，  可以控制相对于谁弹窗。 而 Layer弹窗默认是屏幕中间
	    		warn("合同领取成功！","investid",100,0);
	     
	    	 }
	     else{
	    		warn("合同失败失败！","investid",100,0);
	    	 
	     }
  
}  

</script>
</html>
