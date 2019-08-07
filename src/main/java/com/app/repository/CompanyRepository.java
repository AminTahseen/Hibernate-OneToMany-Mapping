package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.models.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer>
{
	@Query("SELECT cname FROM Company")
	public List<String> GetCompanyList();
	
	@Query("SELECT cid FROM Company WHERE cname=:cname")
	public int Company_Cid(@Param("cname") String cname);
}
