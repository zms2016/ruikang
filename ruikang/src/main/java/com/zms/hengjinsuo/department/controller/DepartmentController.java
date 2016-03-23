package com.zms.hengjinsuo.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zms.hengjinsuo.bean.TDepartment;
import com.zms.hengjinsuo.department.services.DepartmentService;



@Controller
@RequestMapping("/manager")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@RequestMapping("/getDepartmentList")
	@ResponseBody
	private  List<TDepartment>  getDepartmentList()
	{
		
		
		return  departmentService.getDepartmentList();
		
	}

}
