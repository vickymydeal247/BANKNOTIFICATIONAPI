package com.el.DAO;

import com.el.Customer;

public interface BankAPIDAO {

	public boolean saveCustomers(Customer customer);
	
	public boolean isFirstBill(int userid, Customer customer);
	
	public boolean addBillpay(int userid, Customer customer);
	
	public boolean gotFeedBack(int userid);
	
}
