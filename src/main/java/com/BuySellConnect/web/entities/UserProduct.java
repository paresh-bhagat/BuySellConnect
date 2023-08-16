package com.BuySellConnect.web.entities;

import java.util.Date;
import java.util.Objects;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="User_Products")
public class UserProduct {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="Product_id")
	private int productId;
	
	@Column(length=70,name="Product_Title")
	@Size(min=1,max=70,message="Title between 1 to 70 characters")
	private String productTitle;
	
	@Column(length=60,name="Product_Category")
	@Size(min=1,max=60,message="Title between 1 to 60 characters")
	private String productCategory;
	
	@Column(length=70,name="Product_Overview")
	@Size(min=1,max=70,message="Overview between 1 to 70 characters")
	private String productOverview;
	
	@Column(length=7500,name="Product_Description")
	@Size(min=1,max=7500,message="Content between 1 to 7500 characters")
	private String productDescription;
	
	@Column(name="Product_Price")
	@Max(2147483647)
	@Min(1)
	private int productPrice;

	@Column(name="Product_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date productDate;
	
	@Column(length=60,name="Product_Image")
	@Size(min=1,max=60,message="Image name between 1 to 60 characters")
	private String productImage;
	
	@Column(length=20,name="Product_City")
	@Size(min=1,max=20,message="City name between 1 to 20 characters")
	private String productCity;
	
	@Column(length=20,name="Product_State")
	@Size(min=1,max=20,message="State name between 1 to 20 characters")
	private String productState;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private UserInfo userInfo;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductOverview() {
		return productOverview;
	}

	public void setProductOverview(String productOverview) {
		this.productOverview = productOverview;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductCity() {
		return productCity;
	}

	public void setProductCity(String productCity) {
		this.productCity = productCity;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	

	public UserProduct(int productId,
			@Size(min = 1, max = 70, message = "Title between 1 to 70 characters") String productTitle,
			@Size(min = 1, max = 60, message = "Title between 1 to 60 characters") String productCategory,
			@Size(min = 1, max = 70, message = "Overview between 1 to 70 characters") String productOverview,
			@Size(min = 1, max = 7500, message = "Content between 1 to 7500 characters") String productDescription,
			@Max(2147483647) @Min(1) int productPrice, Date productDate,
			@Size(min = 1, max = 60, message = "Image name between 1 to 60 characters") String productImage,
			@Size(min = 1, max = 20, message = "City name between 1 to 20 characters") String productCity,
			@Size(min = 1, max = 20, message = "State name between 1 to 20 characters") String productState,
			UserInfo userInfo) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productCategory = productCategory;
		this.productOverview = productOverview;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productDate = productDate;
		this.productImage = productImage;
		this.productCity = productCity;
		this.productState = productState;
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "UserProduct [productId=" + productId + ", productTitle=" + productTitle + ", productCategory="
				+ productCategory + ", productOverview=" + productOverview + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", productDate=" + productDate
				+ ", productImage=" + productImage + ", productCity=" + productCity + ", productState=" + productState
				+ ", userInfo=" + userInfo + "]";
	}

	public UserProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    UserProduct that = (UserProduct) o;
	    return productId == that.productId;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(productId);
	}
}
