package com.BuySellConnect.web.dto;

import java.util.Date;

public class UserProductDto {
	
	private int productId;
    private String productTitle;
    private String productCategory;
    private String productOverview;
    private String productDescription;
    private int productPrice;
    private Date productDate;
    private String productImage;
    private String productCity;
    private String productState;
    
    @Override
	public String toString() {
		return "UserProductDto [productId=" + productId + ", productTitle=" + productTitle + ", productCategory="
				+ productCategory + ", productOverview=" + productOverview + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", productDate=" + productDate
				+ ", productImage=" + productImage + ", productCity=" + productCity + ", productState=" + productState
				+ "]";
	}
	public UserProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProductDto(int productId, String productTitle, String productCategory, String productOverview,
			String productDescription, int productPrice, Date productDate, String productImage, String productCity,
			String productState) {
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
    
}
