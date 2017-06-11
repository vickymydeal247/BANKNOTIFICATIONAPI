package com.el.DAO;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import com.el.Customer;
import com.el.Constants.Constants;

public class BankAPIDAOImpl implements BankAPIDAO{

	private final Log log = LogFactory.getLog(BankAPIDAOImpl.class);
	private RedisTemplate<String, String> redisTemplate;
	private JdbcTemplate jdbcTemplateObject;

	public RedisTemplate<String, String> getRedisTemplate(){
		return redisTemplate;
	}

	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub		
		jdbcTemplateObject = new JdbcTemplate(ds);
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate){
		this.redisTemplate = redisTemplate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.el.DAO.BankAPIDAO#saveCustomers(com.el.Customer)
	 * 
	 * save the value in mysql database
	 * 
	 */
	@Override
	public boolean saveCustomers(Customer customer) {
		// TODO Auto-generated method stub

		jdbcTemplateObject.update(Constants.CREATETABLEFORPAYMENTDETAILS);
		
		String query = "";
		if(customer.getProperties().getText() == null || customer.getProperties().getText().length() == 0){	
			query = Constants.SQLQUERYFORBILLPAYS;
			isFirstBill(customer.getUserid(), customer);
			addBillpay(customer.getUserid(), customer);
			jdbcTemplateObject.update(query, customer.getUserid(), customer.getTs(), customer.getLatlong(), 
					customer.getVerb(), customer.getNoun(), customer.getTimespent(), customer.getProperties().getBank(), 
					customer.getProperties().getMerchantid(), customer.getProperties().getValue(), 
					customer.getProperties().getMode());
		} else{
			query = Constants.SQLQUERYFORTEXTS;
			gotFeedBack(customer.getUserid());
			jdbcTemplateObject.update(query, customer.getUserid(), customer.getTs(), customer.getLatlong(), 
					customer.getVerb(), customer.getNoun(), customer.getTimespent(),
					customer.getProperties().getText());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.el.DAO.BankAPIDAO#isFirstBill(int)
	 * 
	 * check it's users first pay, check with key in redis if it's null it's first pay
	 * and set a value 1 in the key ("FistPay_userid")
	 * for same userid next time it will not be null so we identify it
	 * 
	 */
	@Override
	public boolean isFirstBill(int userid, Customer customer) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set("" + userid, "1", 15, TimeUnit.MINUTES);
		String isPresent = redisTemplate.opsForValue().get(Constants.USERID + userid);
		if(isPresent != null && isPresent.equalsIgnoreCase("1")){
			return false;
		} else{
			log.warn(" this is the first payment of userid " + userid + " " + customer.toString());
			redisTemplate.opsForValue().set(Constants.USERID + userid, "1");
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.el.DAO.BankAPIDAO#addBillpay(int, double)
	 * 
	 * adding payment count and payment value for current five minutes
	 * in redis, each payment after every  mins will be expired using redis expired key concepts
	 * and we have data only for last five minutes in redis
	 * 
	 */
	@Override
	public boolean addBillpay(int userid, Customer customer) {
		// TODO Auto-generated method stub
		String key = Constants.BILLPAYS + userid + Constants.UNDERSCORE + getRandow() + 
				Constants.UNDERSCORE + customer.getProperties().getValue();
		redisTemplate.opsForValue().set(key, "1", 5, TimeUnit.MINUTES);
		String billPayments = redisTemplate.opsForValue().get(Constants.BILLCOUNTS + userid);
		String payValue = "";
		if(billPayments != null){
			String vik[] = billPayments.split(":");
			int count = Integer.parseInt(vik[0]);
			double value = Double.parseDouble(vik[1]);
			count = count + 1;
			value = value + customer.getProperties().getValue();
			if(count >= 5 && value >= 20000){
				log.warn(" in last 5 minutes this customer of userid " + userid + " has pay for " + count + " times value of total " + value);
			}
			payValue = count + ":" + value;
			redisTemplate.opsForValue().set(Constants.BILLCOUNTS + userid, payValue);
		} else{ // if there is no value
			payValue = 1 + ":" + customer.getProperties().getValue();
			redisTemplate.opsForValue().set(Constants.BILLCOUNTS + userid, payValue);
		}
		return false;
	}

	private static String getRandow() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args){
		String vicky = "vicky";
		BankAPIDAOImpl bankAPIDAOImpl = new BankAPIDAOImpl();
		bankAPIDAOImpl.getRedisTemplate().opsForValue().set(vicky, "vicky");
		vicky = bankAPIDAOImpl.getRedisTemplate().opsForValue().get("vicky");
		System.out.println(vicky);
	}

	/*
	 * (non-Javadoc)
	 * @see com.el.DAO.BankAPIDAO#gotFeedBack(int)
	 * 
	 * delete the key if got the feedback
	 * 
	 */
	@Override
	public boolean gotFeedBack(int userid) {
		// TODO Auto-generated method stub
		redisTemplate.delete("" + userid);
		return true;
	}

}
