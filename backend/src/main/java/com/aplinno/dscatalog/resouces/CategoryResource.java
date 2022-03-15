package com.aplinno.dscatalog.resouces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplinno.dscatalog.entities.category;


@RestController
@RequestMapping(value ="/categories")
public class CategoryResource {
   
	@GetMapping
	public ResponseEntity<List <category>>findAll(){
		
		List<category> list = new ArrayList<>();
		
		list.add(new category(1L,"Books"));
		list.add(new category(2L,"Electonics"));
		
		return ResponseEntity.ok().body(list);
		} 
}
