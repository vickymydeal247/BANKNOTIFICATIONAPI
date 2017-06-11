package com.el.MessageSubscribers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.el.Constants.Constants;

import redis.clients.jedis.JedisPubSub;

public class MessageSubscribers extends JedisPubSub{

	private final Log log = LogFactory.getLog(MessageSubscribers.class);
	private RedisTemplate<String, String> redisTemplate;

	public RedisTemplate<String, String> getRedisTemplate(){
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate){
		this.redisTemplate = redisTemplate;
	}

	/*
	 * (non-Javadoc)
	 * @see redis.clients.jedis.JedisPubSub#onPMessage(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * it's the user who's not submit feedback within 15 minutes
	 * 
	 */
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub
		if(channel.equalsIgnoreCase(Constants.expiryChannel)){
			if(message.contains(Constants.BILLPAYS)){
				String vik[] = message.split(message);
				String userid = vik[1];
				double pay = Double.parseDouble(vik[3]);
				String billPayments = redisTemplate.opsForValue().get(Constants.BILLCOUNTS + userid);
				String vicky[] = billPayments.split(":");
				int count = Integer.parseInt(vicky[0]) - 1;
				if(count > 0){
					double value = Double.parseDouble(vicky[1]) - pay;
					String payValue = count + ":" + value;
					redisTemplate.opsForValue().set(Constants.BILLCOUNTS + userid, payValue);
				}
			} else{
				log.warn(" this userid did not submited the feedback after 15 mins also " + message);
			}
		}
	}

	@Override
	public void onMessage(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
