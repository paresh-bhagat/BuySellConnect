package com.BuySellConnect.web.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


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
	
	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="userProduct",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ProductFeature> productFeatures;
	
	@ManyToOne
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

	public List<ProductFeature> getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(List<ProductFeature> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserProduct(int productId, String productTitle, String productCategory, String productOverview,
			String productDescription, Date productDate, String productImage, String productCity, String productState,
			List<ProductFeature> productFeatures, UserInfo userInfo) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productCategory = productCategory;
		this.productOverview = productOverview;
		this.productDescription = productDescription;
		this.productDate = productDate;
		this.productImage = productImage;
		this.productCity = productCity;
		this.productState = productState;
		this.productFeatures = productFeatures;
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "UserProduct [productId=" + productId + ", productTitle=" + productTitle + ", productCategory="
				+ productCategory + ", productOverview=" + productOverview + ", productDescription="
				+ productDescription + ", productDate=" + productDate + ", productImage=" + productImage + ", productCity="
				+ productCity + ", productState=" + productState + ", productFeatures=" + productFeatures
				+ ", userInfo=" + userInfo + "]";
	}

	public UserProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
