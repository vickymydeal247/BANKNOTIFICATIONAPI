package com.el.Constants;

public class Constants {

	public static final String expiryChannel = "__keyevent@0__:expired";
	public static final String VALID = "valid";
	public static final String USERID = "USERID_";
	public static final String BILLCOUNTS = "BILLCOUNTS:VALUE_";
	public static final String USERID_BILLPAY = "USERID_BILLPAY";
	public static final String UNDERSCORE = "_";
	public static final String BILLPAYS = "BILLPAYS_";
	public static final String CREATETABLEFORPAYMENTDETAILS = " CREATE TABLE IF NOT EXISTS Customer (userid int(10) NOT NULL, "
			+ " ts text NOT NULL,  latlong text NOT NULL, verb text NOT NULL, noun text NOT NULL, timespent int(10) NOT NULL, "
			+ " bank text NULL, merchantid text NULL, value decimal(10) null, mode text null, texts text null) ";
	public static final String SQLQUERYFORBILLPAYS = "INSERT INTO Customer (userid, ts, latlong, verb, noun, "
			+ " timespent, bank, merchantid, value, mode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SQLQUERYFORTEXTS = "INSERT INTO Customer (userid, ts, latlong, verb, noun, "
			+ "timespent, texts) VALUES (?, ?, ?, ?, ?, ?, ?)";

}
