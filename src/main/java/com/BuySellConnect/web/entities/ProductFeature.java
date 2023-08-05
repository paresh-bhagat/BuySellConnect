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
@Table(name="Product_Features")
public class ProductFeature {

	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	@Column(name="Feature_id")
	private int featureId;
	
	@Column(length=70,name="Feature_Title")
	private String featureTitle;
	
	@Column(length=60,name="Feature_Content")
	private String featureContent;
	
	@ManyToOne
	private UserProduct userProduct;

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public String getFeatureTitle() {
		return featureTitle;
	}

	public void setFeatureTitle(String featureTitle) {
		this.featureTitle = featureTitle;
	}

	public String getFeatureContent() {
		return featureContent;
	}

	public void setFeatureContent(String featureContent) {
		this.featureContent = featureContent;
	}

	public UserProduct getUserProduct() {
		return userProduct;
	}

	public void setUserProduct(UserProduct userProduct) {
		this.userProduct = userProduct;
	}

	@Override
	public String toString() {
		return "ProductFeature [featureId=" + featureId + ", featureTitle=" + featureTitle + ", featureContent="
				+ featureContent + ", userProduct=" + userProduct + "]";
	}

	public ProductFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductFeature(int featureId, String featureTitle, String featureContent, UserProduct userProduct) {
		super();
		this.featureId = featureId;
		this.featureTitle = featureTitle;
		this.featureContent = featureContent;
		this.userProduct = userProduct;
	}
	
}
