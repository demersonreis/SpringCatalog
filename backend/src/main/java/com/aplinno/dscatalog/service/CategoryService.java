package com.aplinno.dscatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aplinno.dscatalog.dto.CategoryDTO;
import com.aplinno.dscatalog.entities.category;
import com.aplinno.dscatalog.repositories.CategoryRepository;
import com.aplinno.dscatalog.service.exceptions.EntityNotFaundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {

		List<category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {

		Optional<category> obj = repository.findById(id);
		  category entity = obj.orElseThrow(()
				  -> new EntityNotFaundException("Entity not found!"));
		return new CategoryDTO(entity);

	}

}
