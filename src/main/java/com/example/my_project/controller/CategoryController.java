package com.example.my_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.my_project.dto.CategoryDTO;
import com.example.my_project.entity.Category;
import com.example.my_project.service.CategoryService;
import com.example.my_project.tranform.CategoryTranform;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/categorys")
public class CategoryController {
	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	@GetMapping
	public List<CategoryDTO> allCategorys() {
		CategoryTranform categoryTranform = new CategoryTranform();
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		List<Category> categories = categoryService.findAll();
		for (Category c : categories) {
			CategoryDTO categoryDTO = categoryTranform.apply(c);
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}
	@PostMapping
	public Category insertCategory(@RequestBody CategoryDTO categoryDTO) {
		CategoryTranform categoryTranform = new CategoryTranform();
		categoryService.insert(categoryTranform.apply(categoryDTO));
		return categoryTranform.apply(categoryDTO);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@RequestBody CategoryDTO dto, @PathVariable Long id) {
		CategoryTranform categoryTranform = new CategoryTranform();
		Category category = categoryService.findById(id);
		categoryTranform.apply(category, dto);
		categoryService.update(category);
		return category;
	}

	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable long id) {
		categoryService.delete(id);
		return "ok";
	}

}
