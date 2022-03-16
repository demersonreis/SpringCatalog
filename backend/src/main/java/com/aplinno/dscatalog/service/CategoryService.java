package com.aplinno.dscatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplinno.dscatalog.entities.category;
import com.aplinno.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
    
	@Autowired
	private CategoryRepository repository;
	
	public List<category> findAll(){
		
		return repository.findAll();
	}
	
}
