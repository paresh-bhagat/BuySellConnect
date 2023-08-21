package com.BuySellConnect.web.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Interests")
public class Interest {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="Interest_id")
	private int interestId;
	
	@Column(name="Interested_Product_id")
	private int interestedProductId;
	
	@Column(length=70,name="Product_Seller")
	private String productSeller;
	
	@Column(name="Interest_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date interestDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo userInfo;

	public int getInterestId() {
		return interestId;
	}

	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}

	public String getProductSeller() {
		return productSeller;
	}

	public void setProductSeller(String productSeller) {
		this.productSeller = productSeller;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	
	public int getInterestedProductId() {
		return interestedProductId;
	}

	public void setInterestedProductId(int interestedProductId) {
		this.interestedProductId = interestedProductId;
	}

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}
	
	
	public Interest(int interestId, int interestedProductId, String productSeller, Date interestDate,
			UserInfo userInfo) {
		super();
		this.interestId = interestId;
		this.interestedProductId = interestedProductId;
		this.productSeller = productSeller;
		this.interestDate = interestDate;
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "Interest [interestId=" + interestId + ", interestedProductId=" + interestedProductId
				+ ", productSeller=" + productSeller + ", interestDate=" + interestDate + ", userInfo=" + userInfo
				+ "]";
	}

	public Interest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
