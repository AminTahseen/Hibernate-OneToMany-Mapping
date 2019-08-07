package com.app.services.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Company;
import com.app.models.Products;
import com.app.repository.CompanyRepository;
import com.app.services.interfaces.CompanyServices;

@Service
public class CompanyServicesImpl implements CompanyServices{

	@Autowired
	CompanyRepository company_repo;
	
	@Transactional
	@Override
	public List<Company> GetCompanyList()
	{
		List<Company> companies=(List<Company>) company_repo.findAll();
		return companies;
	}

	@Transactional
	@Override
	public void SaveCompany(Company company) 
	{
		company_repo.save(company);
	}

	@Transactional
	@Override
	public void DeleteCompany(int cid) 
	{
		Company company=company_repo.findById(cid).get();
		company_repo.delete(company);
	}

	@Transactional
	@Override
	public void EditCompany(int cid, String cname,List<Products> products) 
	{
		Company company=company_repo.findById(cid).get();
		company.setCname(cname);
		company.setProducts(products);
		company_repo.save(company);
	}

	@Transactional
	@Override
	public Company getCompany(int cid) 
	{
		Company company=company_repo.findById(cid).get();
		return company;
	}
	
	@Transactional
	@Override 
	public List<String> getCompanyNames() 
	{
		List<String> lst=company_repo.GetCompanyList();
		return lst;
	}

	@Override
	public int getCid(String cname) 
	{
		int cid=company_repo.Company_Cid(cname);
		return cid;
	}

}
