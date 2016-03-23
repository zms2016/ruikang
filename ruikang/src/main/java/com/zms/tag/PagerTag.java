package com.zms.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

  /**
   * 
   * 功能说明：自定义的 分页标签   前台可以直接用标签 做分页页码打印和导航
   * 创建人：330140511@qq.com  
   * 创建时间：2015年10月9日/上午9:54:04
   */
public class PagerTag extends TagSupport {
 
	private static final long serialVersionUID = 1L;

	private String url;         //请求的路径URL 可以吧参数写在后面
	private int pageSize = 10;  //每页要显示的记录数
	private int currPage = 1;     //当前页号
	private int totalRecords;    //总记录数

	public int doStartTag() throws JspException {
	
		int pageCount = (totalRecords + pageSize - 1) / pageSize;  //计算总页数
		
		//拼写要输出到页面的HTML文本
		StringBuilder sb = new StringBuilder();
		if(totalRecords == 0){
			sb.append("<strong>没有可显示的项目</strong>\r\n");
		}else{
			// 请求页码大于总页码，则把请求页码设置为 最大页
			if(currPage > pageCount)
			{		currPage = pageCount;	}
			
			 //请求页小于1 则设置为第一页
			if(currPage < 1)
			{		currPage = 1;	}
			
			
			sb.append("<form method=\"post\" action=\"").append(this.url)
				.append("\" name=\"qPagerForm\">\r\n");
			
		 	//获取请求中的所有参数 由于在JSP中直接在 url后面加了 ？params=ss&title之类的参数，所以这里不需要再解析参数
			/*HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			Enumeration<String> enumeration = request.getParameterNames();
			String name = null;  //参数名
			String value = null; //参数值
			//把请求中的所有参数当作隐藏表单域
			while (enumeration.hasMoreElements()) {
				name =  enumeration.nextElement();
				value = request.getParameter(name);
				// 去除页号
				if (name.equals("currPage")) {
					if (null != value && !"".equals(value)) {
						currPage = Integer.parseInt(value);
					}
					continue;
				}
				sb.append("<input type=\"hidden\" name=\"")
				  .append(name)
				  .append("\" value=\"")
				  .append(value)
				  .append("\"/>\r\n");
			} */
	
			// 把当前页号设置成请求参数
			sb.append("<input type=\"hidden\" name=\"").append("currPage")
				.append("\" value=\"").append(currPage).append("\"/>\r\n");
			
			// 输出统计数据
			sb.append("<span style=\"color:#999999\">&nbsp;共<strong>").append(totalRecords)
				.append("</strong>条记录,")
				.append("分为<strong>")
				.append(pageCount)
				.append("</strong>页&nbsp; </span>\r\n");
			    
			sb.append(" <nav style=\"float:right;margin-right:40px\">");
			sb.append("<ul class=\"pagination\">");
			
	
			
			//上一页处理
			if (currPage == 1) {
				sb.append(" 	<li class=\"disabled\"> <a href=\"javascript:void(0)\" aria-label=\"Previous\" >  <span aria-hidden=\"true\">&laquo;</span>   </a> </li>\r\n");
			} else {
				sb.append("<li><a href=\"javascript:turnOverPage(")
				  .append((currPage - 1))
				  .append(")\">&laquo;&nbsp;上一页</a></li>\r\n");
			}
			
			//如果前面页数过多,显示"..."
			int start = 1; 
			if(this.currPage > 4){
				start = this.currPage - 1;
				sb.append("<li><a href=\"javascript:turnOverPage(1)\">1</a></li>\r\n");
				sb.append("<li><a href=\"javascript:turnOverPage(2)\">2</a></li>\r\n");
				sb.append("<li><a href=\"javascript:void(0)\">&hellip;</a></li>\r\n");
			}
			//显示当前页附近的页
			int end = this.currPage + 1;
			if(end > pageCount){
				end = pageCount;
			}
			for(int i = start; i <= end; i++){
				if(currPage == i){   //当前页号不需要超链接
					sb.append("<li class=\"active\"> <a href=\"javascript:void(0)\">")
						.append(i)
						.append(" </a></li>\r\n");
				}else{
					sb.append("<li><a href=\"javascript:turnOverPage(")
						.append(i)
						.append(")\">")
						.append(i)
						.append("</a></li>\r\n");
				}
			}
			//如果后面页数过多,显示"..."
			if(end < pageCount - 2){
				sb.append("<li><a href=\"javascript:void(0)\">&hellip;</a></li>\r\n");
			}
			if(end < pageCount - 1){
				sb.append("<li><a href=\"javascript:turnOverPage(")
				.append(pageCount - 1)
				.append(")\">")
				.append(pageCount - 1)
				.append("</a></li>\r\n");
			}
			if(end < pageCount){
				sb.append("<li><a href=\"javascript:turnOverPage(")
				.append(pageCount)
				.append(")\">")
				.append(pageCount)
				.append("</a></li>\r\n"); 
			}
			
			//下一页处理
			if (currPage == pageCount) {
				sb.append(" 	<li class=\"disabled\"> <a href=\"javascript:void(0)\" aria-label=\"Next\">  <span aria-hidden=\"true\">&raquo;</span>   </a> </li>\r\n");
			} else {
				sb.append("<li><a href=\"javascript:turnOverPage(")
					.append((currPage + 1))
					.append(")\">&nbsp;&raquo;</a></li>\r\n");
			}
			
			sb.append("</ul></nav>");
			
			sb.append("</form>\r\n");
	
			// 生成提交表单的JS
			sb.append("<script language=\"javascript\">\r\n");
			sb.append("  function turnOverPage(no){\r\n");
			sb.append("    if(no>").append(pageCount).append("){");
			sb.append("      no=").append(pageCount).append(";}\r\n");
			sb.append("    if(no<1){no=1;}\r\n");
			sb.append("    document.qPagerForm.currPage.value=no;\r\n");
			sb.append("    document.qPagerForm.submit();\r\n");
			sb.append("  }\r\n");
			sb.append("</script>\r\n");
		}

		
		//把生成的HTML输出到响应中
		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;  //本标签主体为空,所以直接跳过主体
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

 
 
} 