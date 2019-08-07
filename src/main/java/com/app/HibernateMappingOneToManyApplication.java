package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.models.Company;
import com.app.models.Products;
import com.app.services.interfaces.CompanyServices;
import com.app.services.interfaces.ProductsServices;

@SpringBootApplication
public class HibernateMappingOneToManyApplication{

	@Autowired
	CompanyServices companyService;
	
	@Autowired 
	ProductsServices productService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HibernateMappingOneToManyApplication.class, args);
	}

	public void Save() 
	{
	Company company=new Company("Nike");
	
	Products shoe1=new Products("Air Buffers Shoes",company);
	Products shoe2=new Products("Black Knight Shoes",company);
	Products shoe3=new Products("White Busters Shoes",company);

	List<Products> prod=new ArrayList<Products>();
	prod.add(shoe1);
	prod.add(shoe2);
	prod.add(shoe3);
	
	company.setProducts(prod);
	
	companyService.SaveCompany(company);
	}
	
	public void getList() 
	{
		List<Company> companylist=companyService.GetCompanyList();
		List<Products> productlist=productService.GetProductsList();
		
        System.out.println("===================Product List:==================");
		  productlist.forEach(System.out::println);
		 
	     System.out.println("===================Company List:==================");
	      companylist.forEach(System.out::println);
		
	}

}
