package com.BuySellConnect.web.dto;

import java.util.Date;

public class InterestDto {

	private int interestId;
	
	private int interestedProductId;
	
	private String productSeller;
	
	private Date interestDate;

	public int getInterestId() {
		return interestId;
	}

	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}

	public int getInterestedProductId() {
		return interestedProductId;
	}

	public void setInterestedProductId(int interestedProductId) {
		this.interestedProductId = interestedProductId;
	}

	public String getProductSeller() {
		return productSeller;
	}

	public void setProductSeller(String productSeller) {
		this.productSeller = productSeller;
	}

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}

	@Override
	public String toString() {
		return "InterestDto [interestId=" + interestId + ", interestedProductId=" + interestedProductId
				+ ", productSeller=" + productSeller + ", interestDate=" + interestDate + "]";
	}

	public InterestDto(int interestId, int interestedProductId, String productSeller, Date interestDate) {
		super();
		this.interestId = interestId;
		this.interestedProductId = interestedProductId;
		this.productSeller = productSeller;
		this.interestDate = interestDate;
	}

	public InterestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
