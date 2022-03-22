package com.aplinno.dscatalog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aplinno.dscatalog.dto.CategoryDTO;
import com.aplinno.dscatalog.entities.category;
import com.aplinno.dscatalog.repositories.CategoryRepository;
import com.aplinno.dscatalog.service.exceptions.DataBaseException;
import com.aplinno.dscatalog.service.exceptions.ResouceNotFaundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {

		Page<category> list = repository.findAll(pageRequest);
		return list.map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {

		Optional<category> obj = repository.findById(id);
		category entity = obj.orElseThrow(() -> new ResouceNotFaundException("Entity not found!"));
		return new CategoryDTO(entity);

	}

	@Transactional
	public CategoryDTO categoryInsert(CategoryDTO dto) {

		category entity = new category();
		entity.setName(dto.getName());

		entity = repository.save(entity);

		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO categoryUpdate(Long id, CategoryDTO dto) {

		try {
			category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);

		} catch (EntityNotFoundException e) {

			throw new ResouceNotFaundException("Id not found " + id);
		}

	}

	public void categoryDelete(Long id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

			throw new ResouceNotFaundException("Id not found " + id);

		} catch (DataIntegrityViolationException e) {

			throw new DataBaseException("Integrity Violation");

		}

	}

}
