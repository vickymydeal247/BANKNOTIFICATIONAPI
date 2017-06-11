package com.el;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class Customer {

	@JsonProperty
	private String noun;
	@JsonProperty
	private int userid;
	@JsonProperty
	private String ts;
	@JsonProperty
	private String latlong;
	@JsonProperty
	private String verb;
	@JsonProperty
	private int timespent;
	@JsonProperty
	private Properties properties;
	public Customer() {
		super();
	}
	public Customer(String noun, int userid, String ts, String latlong, String verb, int timespent,
			Properties properties) {
		super();
		this.noun = noun;
		this.userid = userid;
		this.ts = ts;
		this.latlong = latlong;
		this.verb = verb;
		this.timespent = timespent;
		this.properties = properties;
	}
	public String getNoun() {
		return noun;
	}
	public void setNoun(String noun) {
		this.noun = noun;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getLatlong() {
		return latlong;
	}
	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
	public int getTimespent() {
		return timespent;
	}
	public void setTimespent(int timespent) {
		this.timespent = timespent;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
