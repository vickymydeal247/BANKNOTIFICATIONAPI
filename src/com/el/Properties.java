package com.el;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class Properties {

	@JsonProperty
	private String bank;
	@JsonProperty
	private String merchantid;
	@JsonProperty
	private double value;
	@JsonProperty
	private String mode;
	@JsonProperty
	private String text;
	public Properties(String bank, String merchantid, double value, String mode) {
		super();
		this.bank = bank;
		this.merchantid = merchantid;
		this.value = value;
		this.mode = mode;
	}
	public Properties(String text) {
		super();
		this.text = text;
	}
	public Properties() {
		super();
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
