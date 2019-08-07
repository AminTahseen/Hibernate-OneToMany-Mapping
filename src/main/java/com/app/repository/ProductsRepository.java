package com.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.models.Products;

public interface ProductsRepository extends CrudRepository<Products, Integer> 
{
	@Query("SELECT pid FROM Products WHERE pname=:pname")
	public int GetProductIdByName(@Param("pname") String pname);
	
}
