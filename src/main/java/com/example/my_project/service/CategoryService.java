package com.example.my_project.service;

import java.util.List;

import com.example.my_project.entity.Category;

public interface CategoryService {
List<Category> findAll();
	
	Category findByCategoryCode(String code);

	Category findById(long id);

	void insert(Category c);

	void update(Category c);

	void delete(Long id);
}
