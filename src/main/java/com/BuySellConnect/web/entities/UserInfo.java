package com.BuySellConnect.web.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Pattern(regexp="(0/91)?[7-9][0-9]{9}",message="Invalid phone number!")
	private String mobileNumber;
	
	@Column(length=50,name="User_Email",unique=true)
	@Size(min=3,max=50,message="Email between 1 to 50 characters")
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Invalid email id!")
	private String email;
	
	@OneToMany(mappedBy="userInfo",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<UserProduct> products;
	
	@Column(length=10,name="User_Role")
	private String role;
	
	@OneToMany(mappedBy="userInfo",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Order> orders;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
				+ ", email=" + email + ", products=" + products + ", role=" + role + ", orders=" + orders + "]";
	}

	public UserInfo(@Size(min = 1, max = 20, message = "Username between 1 to 20 characters") String username,
			@Size(min = 1, max = 20, message = "Password between 1 to 20 characters") String password,
			@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "Invalid phone number!") String mobileNumber,
			@Size(min = 3, max = 50, message = "Email between 1 to 50 characters") @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email id!") String email,
			List<UserProduct> products, String role, List<Order> orders) {
		super();
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.products = products;
		this.role = role;
		this.orders = orders;
	}

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    UserInfo userInfo = (UserInfo) o;
	    return Objects.equals(username, userInfo.username);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(username);
	}
}
