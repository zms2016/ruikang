package com.zms.hengjinsuo.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


 
@Controller
public class ProgramController {

	
	
	@RequestMapping("/404")
	public  String  notFindPage()
	{
		
		
		 return "404";
	}
	
	
 
	
}
