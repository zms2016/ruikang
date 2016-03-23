package com.zms.hengjinsuo.department.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TDepartment;
import com.zms.hengjinsuo.bean.TDepartmentExample;
import com.zms.hengjinsuo.dao.TDepartmentMapper;


@Service
public class DepartmentService {
	
	
	
	@Autowired
	private TDepartmentMapper departmentMapper;

	
	 //显示 部门列表
	public List<TDepartment> getDepartmentList() {
		 
		TDepartmentExample example=new TDepartmentExample();
		TDepartmentExample.Criteria criteria=example.createCriteria();
		criteria.andIsuseEqualTo( 0);
		
		example.setOrderByClause(" sortid ");
		
		
		return departmentMapper.selectByExample(example);
	}
	
	//根据id 获取 实体
	
	public TDepartment geTDepartmentById(Integer id)
	{
		
		return  departmentMapper.selectByPrimaryKey(id);
	}

}
