package com.risen.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risen.service.CategoryService;

/**
 * @author sen
 *	分类模块 控制层
 */

@Controller
public class CategoryController {
	@Resource
	private CategoryService categoryService;
	
	@RequestMapping("category")
	public String getAllCategories(HttpServletRequest request){
		request.setAttribute("parentList",categoryService.getAllCategories());
		return "front/category";
	}
}
