package com.risen.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.risen.bean.Category;
import com.risen.dao.CategoryMapper;

/**
 * @author sen
 *	分类模块 Service层
 */
@Service
public class CategoryService {
	@Resource
	private CategoryMapper categoryMapper;
	
	public List<Category> getAllCategories(){
		List<Category> parentList=categoryMapper.getAllCategories();
		return parentList;
	}
}
