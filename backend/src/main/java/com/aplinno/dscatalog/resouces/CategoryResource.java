package com.aplinno.dscatalog.resouces;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aplinno.dscatalog.dto.CategoryDTO;
import com.aplinno.dscatalog.entities.category;
import com.aplinno.dscatalog.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {

		List<CategoryDTO> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findAllById(@PathVariable Long id) {

		CategoryDTO dtaById = service.findById(id);

		return ResponseEntity.ok().body(dtaById);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> categoryInsert(@RequestBody CategoryDTO dto) {

		dto = service.categoryInsert(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> categoryUpdate(@PathVariable Long id, @RequestBody CategoryDTO dto) {

		dto = service.categoryUpdate(id, dto);
		return ResponseEntity.ok().body(dto);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> categoryDelete(@PathVariable Long id) {

		service.categoryDelete(id);
		return ResponseEntity.noContent().build();

	}
}
