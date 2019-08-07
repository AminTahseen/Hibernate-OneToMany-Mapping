package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.models.Company;
import com.app.models.Products;
import com.app.services.interfaces.CompanyServices;
import com.app.services.interfaces.ProductsServices;

@Controller
public class AppController {
	
	@Autowired
	ProductsServices service_prod;
	
	@Autowired
	CompanyServices service_comp;
	
	private static final Logger LOGGER = Logger.getLogger("AppController");

	
	@RequestMapping("")
	public String GetDefault(Model map) 
	{
		LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE DEFAULT ' ' ****");
		try 
		{
			LOGGER.log(Level.INFO, "FETCHING LIST OF COMPANY & PRODUCTS...");
			List<Company> companylist=service_comp.GetCompanyList();
			
			LOGGER.log(Level.INFO, "LIST FETCHED, ASSIGNING IT TO MODEL 'list' ");
			map.addAttribute("list",companylist);
		}
		catch (Exception e) 
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "list.jsp";
	}
	
	@RequestMapping("/create")
	public String CompanyCreate() 
	{
		return "companyCreate.jsp";
	}
	
	@RequestMapping("/createProd")
	public String ProductCreate(Model map) 
	{
        LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/createProd' ****");
		try 
		{
	        LOGGER.log(Level.INFO, "GETTING LIST OF COMPANY NAMES...");
			List<String> cnames=service_comp.getCompanyNames();
			
	        LOGGER.log(Level.INFO, "LIST FETCHED, ASSIGNING IT TO MODEL 'cname'  ");
			map.addAttribute("cname",cnames);
		}
		catch (Exception e) 
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "productCreate.jsp";
	}
	
	@RequestMapping("/saveCompany")
	public String SaveCompany(@RequestParam("cname") String cname,Model map) 
	{
		LOGGER.log(Level.INFO, "   ");
			
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/saveCompany' ****");
		try 
		{
	        LOGGER.log(Level.INFO, "CREATING NEW COMPANY OBJECT 'com' ");
			Company com=new Company(cname);

			LOGGER.log(Level.INFO, "OBJECT 'com' CREATED, SAVING TO DATABASE...");
			service_comp.SaveCompany(com);
	        LOGGER.log(Level.INFO, "COMPANY DATA SAVED !");

			LOGGER.log(Level.INFO, "FETCHING LIST OF COMPANY & PRODUCTS...");
			List<Company> companylist=service_comp.GetCompanyList();
			
			LOGGER.log(Level.INFO, "LIST FETCHED, ASSIGNING IT TO MODEL 'list' ");
			map.addAttribute("list",companylist);
		}
		catch (Exception e) 
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "list.jsp";
	}
	
	@RequestMapping("/saveProducts")
	public String SaveProduct(
			                  @RequestParam("pname") String pname,
			                  @RequestParam("cname") String cname,
			                  Model map
			                 ) 
	{
		LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/saveProducts' ****");
		try 
		{
			LOGGER.log(Level.INFO, "GETTING COMPANY WITH NAME "+cname+"...");
			Company company=service_comp.getCompany(service_comp.getCid(cname));
			LOGGER.log(Level.INFO, "COMPANY FOUND, DATA [ "+company.toString()+" ]");

			
			LOGGER.log(Level.INFO, "CREATING NEW PRODUCT ' "+pname+" ' FOR COMPANY ' "+company.getCname()+" '...");
			Products prod=new Products(pname,company);  
			
			LOGGER.log(Level.INFO, "CREATING LIST OF PRODUCTS...");
			List<Products> prods=new ArrayList<Products>();
			
			LOGGER.log(Level.INFO, "ADDING PRODUCT TO LIST OF PRODUCTS...");
			prods.add(prod);  

			LOGGER.log(Level.INFO, "SETTING PRODUCT LIST TO COMPANY ' "+company.getCname()+" '...");
			company.setProducts(prods); 
			
			LOGGER.log(Level.INFO, "SAVING TO DATABASE...");
			service_comp.SaveCompany(company);
			
			LOGGER.log(Level.INFO, "SAVED TO DATABASE !");
			
			map.addAttribute("msg", "Product "+pname+" Added in Company "+cname);
			
			LOGGER.log(Level.INFO, "GETTING LIST OF COMPANY NAMES...");
			List<String> cnames=service_comp.getCompanyNames();
				
		    LOGGER.log(Level.INFO, "LIST FETCHED, ASSIGNING IT TO MODEL 'cname'  ");
		    map.addAttribute("cname",cnames);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "productCreate.jsp";
	}
	
	@RequestMapping("/findCompany")
	public String findCompany(@RequestParam("cname") String cname,Model map) 
	{
		LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/findCompany' ****");
		try 
		{
			 LOGGER.log(Level.INFO, "FINDING COMPANY ID WITH NAME ' "+cname+" '...");
			 int cid=service_comp.getCid(cname);	
			 
			 LOGGER.log(Level.INFO, "ID FOUND, FINDING COMPANY BY ID...");
			 Company c=service_comp.getCompany(cid); 
			 LOGGER.log(Level.INFO, "COMPANY FOUND, DATA [ "+c.toString()+" ]");
			 
			 LOGGER.log(Level.INFO, "ADDING REQUIRED DATA TO MODEL 'cid','cnam','cname'");
			 map.addAttribute("cid", c.getCid()); 
			 map.addAttribute("cnam", c.getCname()); 
			 map.addAttribute("cname",service_comp.getCompanyNames());
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "editDetails.jsp";
	}
	
	@RequestMapping("/gotoEdit")
	public String GotoEdit(Model map) 
	{
        LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/gotoEdit' ****");
		try 
		{
	        LOGGER.log(Level.INFO, "GETTING LIST OF COMPANY NAMES...");
	        List<String> cnames=service_comp.getCompanyNames();
	        
	        LOGGER.log(Level.INFO, "LIST FETCHED, ASSIGNING IT TO MODEL 'cname' ");
			map.addAttribute("cname",cnames);

		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "editDetails.jsp";
	}
	
	@RequestMapping("/findProduct")
	public String findProduct(@RequestParam("pname") String pname,Model map) 
	{
        LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/findProduct' ****");
		try 
		{
	        LOGGER.log(Level.INFO, "FINDING PRODUCT OF NAME ' "+pname+" '...");
			Products p=service_prod.getProduct(service_prod.getPid(pname));
	        LOGGER.log(Level.INFO, "PRODUCT FOUND, DATA ["+p.toString2()+"]");

	        LOGGER.log(Level.INFO, "ASSIGNING REQUIRED DATA TO MODEL 'pid','pnam','pcomp' ");
			map.addAttribute("pid", p.getPid()); 
			map.addAttribute("pnam", p.getPname()); 
			map.addAttribute("pcomp", p.getCompany().getCname());
			
	        LOGGER.log(Level.INFO, "GETTING LIST OF COMPANY NAMES & ASSIGNING TO MODEL 'cname' ");
			map.addAttribute("cname",service_comp.getCompanyNames());
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "editDetails.jsp";
	}
	
	@RequestMapping("/EditCompany")
	public String EditCompany(
			                  @RequestParam("cid") int cid,
			                  @RequestParam("compname") String cname, 
			                  Model map
			                  ) 
	{
        LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/EditCompany' ****");
		try 
		{
			Company c=service_comp.getCompany(cid);
			c.setCname(cname);
			service_comp.SaveCompany(c);
			List<Company> companylist=service_comp.GetCompanyList();
			map.addAttribute("list",companylist);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "list.jsp";
	}
	
	@RequestMapping("/EditProduct")
	public String EditProduct(
			                 @RequestParam("pid") int pid,
			                 @RequestParam("pname") String pname,
			                 @RequestParam("cname") String cname,
			                 Model map
			                 ) 
	{
		LOGGER.log(Level.INFO, "   ");
			
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/EditProduct' ****");
		try 
		{
		Products p=service_prod.getProduct(pid);
		p.setPname(pname);
		Company c=service_comp.getCompany(service_comp.getCid(cname));
		c.setCname(cname);
		p.setCompany(c);
		service_prod.SaveProduct(p);
		map.addAttribute("msg","Product "+pname+" Updated Successfully !");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "editDetails.jsp";
	}

	@RequestMapping("/gotoDelete")
	public String GotoDelete() 
	{
		return "deleteDetails.jsp";
	}
	
	@RequestMapping("/findCompanyDelete")
	public String findCompanyDelete(@RequestParam("cname") String cname,Model map) 
	{
		LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/findCompanyDelete' ****");
		try 
		{
			LOGGER.log(Level.INFO, "FINDING COMPANY ID OF NAME '"+cname+"'...");
			int cid=service_comp.getCid(cname);	
			
			LOGGER.log(Level.INFO, "ID FOUND, FINDING COMPANY OF ID '"+cid+"'...");
			Company c=service_comp.getCompany(cid); 
			
			LOGGER.log(Level.INFO, "COMPANY FOUND, DATA ["+c.toString()+"] ");
	        LOGGER.log(Level.INFO, "ASSIGNING REQUIRED DATA TO MODEL 'cid','cnam'");
			map.addAttribute("cid", c.getCid()); 
			map.addAttribute("cnam", c.getCname()); 
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "deleteDetails.jsp";
	}
	
	@RequestMapping("/findProductDelete")
	public String findProductDelete(@RequestParam("pname") String pname,Model map) 
	{
		LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/findProductDelete' ****");
		try 
		{
			LOGGER.log(Level.INFO, "SEARCHING FOR PRODUCT WITH NAME '"+pname+"'...");
			Products p=service_prod.getProduct(service_prod.getPid(pname));
			LOGGER.log(Level.INFO, "PRODUCT FOUND, DATA ["+p.toString2()+"]");

			LOGGER.log(Level.INFO, "ASSIGNING REQUIRED DATA TO MODEL 'pid','pnam','pcomp' ");
		    map.addAttribute("pid", p.getPid()); 
			map.addAttribute("pnam", p.getPname()); 
			map.addAttribute("pcomp", p.getCompany().getCname());
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "deleteDetails.jsp";
	}
	
	@RequestMapping("/deleteCompany")
	public String DeleteCompany(@RequestParam("cid") int cid,Model map) 
	{
        LOGGER.log(Level.INFO, "   ");
		
		LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/deleteCompany' ****");
		try 
		{
	        LOGGER.log(Level.INFO, "SEARCHING FOR COMPANY WITH ID '"+cid+"'...");
			Company c=service_comp.getCompany(cid);
	        LOGGER.log(Level.INFO, "COMPANY FOUND, DATA ['"+c.toString()+"']");

	        LOGGER.log(Level.INFO, "DELETING COMPANY WITH ID '"+c.getCid()+"'...");
			service_comp.DeleteCompany(cid);
	        LOGGER.log(Level.INFO, "COMPANY DELETED !");
	        
			map.addAttribute("msg2","Company "+c.getCname()+" Deleted !");
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "deleteDetails.jsp";
	}
	
	@RequestMapping("/deleteProduct")
	public String DeleteProduct(@RequestParam("pid") int pid,Model map) 
	{
	    LOGGER.log(Level.INFO, "   ");
		
	    LOGGER.log(Level.INFO, "**** INSIDE MAPPING '/deleteProduct' ****");
		try 
		{
		    LOGGER.log(Level.INFO, "SEARCHING FOR PRODUCT WITH ID '"+pid+"'...");
			Products p=service_prod.getProduct(pid);
		    LOGGER.log(Level.INFO, "PRODUCT FOUND, DATA ['"+p.toString2()+"']");

		    LOGGER.log(Level.INFO, "DELETING PRODUCT WITH ID '"+p.getPid()+"'");
			service_prod.DeleteProduct(pid);
		    LOGGER.log(Level.INFO, "PRODUCT DELETED !");

			map.addAttribute("msg", "Product "+p.getPname()+" Deleted !");	
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, "An Exception Has Occured ["+e+"]");
		}
		LOGGER.log(Level.INFO, "*****************************");
		return "deleteDetails.jsp";
	}
}
