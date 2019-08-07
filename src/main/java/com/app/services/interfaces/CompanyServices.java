package com.app.services.interfaces;

import java.util.List;

import com.app.models.Company;
import com.app.models.Products;

public interface CompanyServices {
	
	List<Company> GetCompanyList();
	
	void SaveCompany(Company company);
	
	void DeleteCompany(int cid);
	
	void EditCompany(int cid, String cname,List<Products> products);
	
    Company getCompany(int cid);

	List<String> getCompanyNames();
	
	int getCid(String cname);
    
}
