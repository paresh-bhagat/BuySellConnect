package com.BuySellConnect.web.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="User_Products")
public class UserProduct {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="Product_id")
	private int productId;
	
	@Column(length=70,name="Product_Title")
	private String productTitle;
	
	@Column(length=60,name="Product_Category")
	private String productCategory;
	
	@Column(length=60,name="Product_Overview")
	private String productOverview;
	
	@Column(length=7500,name="Product_Content")
	private String productDescription;

	@Column(name="Product_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date productDate;
	
	@Column(length=60,name="Product_Pic")
	private String productPic;
	
	@Column(length=70,name="Product_City")
	private String productCity;
	
	@OneToMany(mappedBy="userProduct",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ProductFeature> productFeatures;
	
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

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
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

	@Override
	public String toString() {
		return "UserProduct [productId=" + productId + ", productTitle=" + productTitle + ", productCategory="
				+ productCategory + ", productOverview=" + productOverview + ", productDescription="
				+ productDescription + ", productDate=" + productDate + ", productPic=" + productPic + ", productCity="
				+ productCity + ", productFeatures=" + productFeatures + ", userInfo=" + userInfo + "]";
	}

	public UserProduct(int productId, String productTitle, String productCategory, String productOverview,
			String productDescription, Date productDate, String productPic, String productCity,
			List<ProductFeature> productFeatures, UserInfo userInfo) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productCategory = productCategory;
		this.productOverview = productOverview;
		this.productDescription = productDescription;
		this.productDate = productDate;
		this.productPic = productPic;
		this.productCity = productCity;
		this.productFeatures = productFeatures;
		this.userInfo = userInfo;
	}

	public UserProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
