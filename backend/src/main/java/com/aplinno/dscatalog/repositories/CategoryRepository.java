package com.aplinno.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplinno.dscatalog.entities.category;

@Repository
public interface  CategoryRepository extends JpaRepository<category, Long>{
   
}
