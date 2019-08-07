package com.app.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	private String pname;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cid")
	private Company company;
	
	public Products() {}

	public Products(String pname, Company company) {
		super();
		this.pname = pname;
		this.company = company;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return  pname;		
	}
	
	public String toString2() {
		return  pid+","+pname+","+company.getCname();		
	}
	
	
	
	
}
