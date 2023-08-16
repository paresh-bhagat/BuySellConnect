package com.BuySellConnect.web.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
public class Order {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="Order_id")
	private int orderId;
	
	@Column(name="Ordered_Product_id")
	private int orderedProductId;
	
	@Column(length=70,name="Ordered_By")
	private String orderedBy;
	
	@Column(length=70,name="Ordered_From")
	private String orderedFrom;
	
	@Column(length=70,name="Address")
	private String address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo userInfo;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}

	public String getOrderedFrom() {
		return orderedFrom;
	}

	public void setOrderedFrom(String orderedFrom) {
		this.orderedFrom = orderedFrom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public int getOrderedProductId() {
		return orderedProductId;
	}

	public void setOrderedProductId(int orderedProductId) {
		this.orderedProductId = orderedProductId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderedProductId=" + orderedProductId + ", orderedBy=" + orderedBy
				+ ", orderedFrom=" + orderedFrom + ", address=" + address + ", userInfo=" + userInfo + "]";
	}

	public Order(int orderId, int orderedProductId, String orderedBy, String orderedFrom, String address,
			UserInfo userInfo) {
		super();
		this.orderId = orderId;
		this.orderedProductId = orderedProductId;
		this.orderedBy = orderedBy;
		this.orderedFrom = orderedFrom;
		this.address = address;
		this.userInfo = userInfo;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
