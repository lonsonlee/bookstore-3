package com.risen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.risen.bean.Category;

/**
 * @author sen
 *	分类模块 dao层
 */
@Repository
public interface CategoryMapper {
	/**
	 * @return
	 * 	返回所有父分类，并且父分类中包含其所有子分类的集合
	 */
	public List<Category> getAllCategories();
}
