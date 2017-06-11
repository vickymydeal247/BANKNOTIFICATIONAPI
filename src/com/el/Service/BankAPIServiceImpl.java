package com.el.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.el.Customer;
import com.el.Properties;
import com.el.DAO.BankAPIDAOImpl;

@Path("/services")
public class BankAPIServiceImpl implements BankAPIService{

	@Autowired
	BankAPIDAOImpl bankAPIDAOImpl;
	
	@Override
	public boolean saveCustomers(String noun, int userid, String ts, String latlong, 
			String verb, int timespent, String bank, String merchantid, Double value, String mode, String text) {
		// TODO Auto-generated method stub
		
		Properties properties;	
		if(text.trim().length() == 0){
			properties = new Properties(bank, merchantid, value, mode);
		} else{
			properties = new Properties(text);
		}		
		Customer customer = new Customer(noun, userid, ts, latlong, verb, timespent, properties);
		return bankAPIDAOImpl.saveCustomers(customer);
	}
	
}
