package com.example.my_project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.my_project.entity.Category;
import com.example.my_project.repository.CategoryRepository;
import com.example.my_project.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findByCategoryCode(String code) {
		return categoryRepository.findByCategoryCode(code);
	}

	@Override
	public Category findById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	@Transactional
	public void insert(Category c) {
		categoryRepository.insert(c);
	}

	@Override
	@Transactional
	public void update(Category c) {
		categoryRepository.update(c);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Category category = categoryRepository.findById(id);
		categoryRepository.delete(category);
	}

}
