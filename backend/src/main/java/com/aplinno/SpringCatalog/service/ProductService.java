package com.aplinno.SpringCatalog.service;

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

import com.aplinno.SpringCatalog.dto.CategoryDTO;
import com.aplinno.SpringCatalog.dto.ProductDTO;
import com.aplinno.SpringCatalog.entities.Product;
import com.aplinno.SpringCatalog.entities.category;
import com.aplinno.SpringCatalog.repositories.CategoryRepository;
import com.aplinno.SpringCatalog.repositories.ProductRepository;
import com.aplinno.SpringCatalog.service.exceptions.DataBaseException;
import com.aplinno.SpringCatalog.service.exceptions.ResouceNotFaundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {

		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {

		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResouceNotFaundException("Entity not found!"));
		return new ProductDTO(entity, entity.getCategories());

	}

	@Transactional
	public ProductDTO ProductInsert(ProductDTO dto) {

		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO ProductUpdate(Long id, ProductDTO dto) {

		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);

		} catch (EntityNotFoundException e) {

			throw new ResouceNotFaundException("Id not found " + id);
		}

	}

	public void ProductDelete(Long id) {

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

			throw new ResouceNotFaundException("Id not found " + id);

		} catch (DataIntegrityViolationException e) {

			throw new DataBaseException("Integrity Violation");

		}

	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());

		entity.getCategories().clear();
		for (CategoryDTO catDto : dto.getCategories()) {

			category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}

	}

}
