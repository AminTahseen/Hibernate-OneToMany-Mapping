package com.app.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Company;
import com.app.models.Products;
import com.app.repository.ProductsRepository;
import com.app.services.interfaces.ProductsServices;

@Service
public class ProductsServicesImpl implements ProductsServices {

	@Autowired
	ProductsRepository product_repo;
	
	@Transactional
	@Override
	public List<Products> GetProductsList() 
	{
		List<Products> list=(List<Products>) product_repo.findAll();
		return list;
	}

	@Transactional
	@Override
	public void SaveProduct(Products product)
	{
		product_repo.save(product);
	}

	@Transactional
	@Override
	public void DeleteProduct(int pid) 
	{
		Products prod=product_repo.findById(pid).get();
		product_repo.delete(prod);
	}

	@Transactional
	@Override
	public void EditProduct(int pid, String pname,Company company) 
	{
		Products product=product_repo.findById(pid).get();
		product.setPname(pname);
		product.setCompany(company);
		product_repo.save(product);
	}

	@Transactional
	@Override
	public Products getProduct(int pid) 
	{
		Products product=product_repo.findById(pid).get();
		return product;
	}

	@Transactional
	@Override
	public int getPid(String pname) 
	{
		int product_id=product_repo.GetProductIdByName(pname);
		return product_id;
	}

}
