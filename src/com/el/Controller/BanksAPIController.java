package com.el.Controller;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.el.Service.BankAPIServiceImpl;

@Controller
@Path("/BanksAPI")
public class BanksAPIController {

	@Autowired
	BankAPIServiceImpl bankAPIServiceImpl;
	
	@RequestMapping(value = { "/saveDetails" }, method = RequestMethod.POST)
	public @ResponseBody Boolean savePaymentDetails(
	@RequestParam(value = "userid", required = true) Integer userid ,
	@RequestParam(value = "ts", required = true) String ts,
	@RequestParam(value = "latlong", required = true) String latlong,
	@RequestParam(value = "noun", required = true) String noun,
	@RequestParam(value = "verb", required = true) String verb,
	@RequestParam(value = "timespent", required = false, defaultValue = "0") String timespent ,
	@RequestParam(value = "bank", required = false, defaultValue = "") String bank,
	@RequestParam(value = "merchantid", required = false, defaultValue = "") String merchantid,
	@RequestParam(value = "value", required = false, defaultValue = "") Double value,
	@RequestParam(value = "mode", required = false, defaultValue = "") String mode,
	@RequestParam(value = "text", required = false, defaultValue = "") String text) throws Exception {
	
		if(timespent.equalsIgnoreCase("null")){
			timespent = "0";
		}
		bankAPIServiceImpl.saveCustomers(noun, userid, ts, latlong, verb, Integer.parseInt(timespent), bank, merchantid, value, mode, text);
		return true;
		
	}
}
