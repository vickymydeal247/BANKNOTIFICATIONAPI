package com.el.Service;

public interface BankAPIService {

	public boolean saveCustomers(String noun, int userid, String ts, String latlong, 
			String verb, int timespent, String bank, String merchantid, Double value, String mode, String text);
	
}
