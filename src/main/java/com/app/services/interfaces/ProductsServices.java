package com.app.services.interfaces;

import java.util.List;

import com.app.models.Company;
import com.app.models.Products;

public interface ProductsServices {

	List<Products> GetProductsList();
	
	void SaveProduct(Products product);
	
	void DeleteProduct(int pid);
	
	void EditProduct(int pid, String pname, Company company);
	
	Products getProduct(int pid);

	int getPid(String pname);

}
