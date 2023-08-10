package com.BuySellConnect.web.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="User_Info")
public class UserInfo {
	
	@Id
	@Column(length=20,name="User_Name")
	@Size(min=1,max=20,message="Username between 1 to 20 characters")
	private String username;
	
	@Column(length=100,name="User_Password")
	@Size(min=1,max=20,message="Password between 1 to 20 characters")
	private String password;
	
	@Column(length=10,name="User_Mobile_Number",unique=true)
	private String mobileNumber;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="userInfo",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<UserProduct> products;
	
	@Column(length=10,name="User_Role")
	private String role;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="userInfo",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> orders;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<UserProduct> getProducts() {
		return products;
	}

	public void setProducts(List<UserProduct> products) {
		this.products = products;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + ", mobileNumber=" + mobileNumber
				+ ", products=" + products + ", role=" + role + ", orders=" + orders + "]";
	}

	public UserInfo(@Size(min = 1, max = 20, message = "Username between 1 to 20 characters") String username,
			@Size(min = 1, max = 20, message = "Password between 1 to 20 characters") String password,
			String mobileNumber, List<UserProduct> products, String role, List<Order> orders) {
		super();
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.products = products;
		this.role = role;
		this.orders = orders;
	}

	public UserInfo() {
		super();
	}
	
	// add product
	
	public void addProduct(UserProduct product){
		this.products.add(product);
	}
		
	// remove product
	public void removeProduct(int i){
		this.products.remove(i);
	}
	
	// add product
	
	public void addOrder(Order order){
		this.orders.add(order);
	}
			
	// remove product
	public void removeOrder(int i){
		this.orders.remove(i);
	}

}
