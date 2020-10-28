package jp.co.internous.mushrooms.model.domain.dto;

import java.sql.Timestamp;

public class CartDto {

	/** TblCart.id -Primary key */
	private long id;

	/** MstProduct.image_full_path*/	
	private String imageFullPath;

	/** MstProduct.product_name*/	
	private String productName;

	/** MstProduct.price */	
	private long price;

	/** TblCart.product_count */	
	private long productCount;

	/** TblCart.product_count × MstProduct.product_price */	
	private long subtotal;

	/** TblCart.created_at */	
	private Timestamp createdAt;

	/** TblCart.updated_at */	
	private Timestamp updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageFullPath() {
		return imageFullPath;
	}

	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getProductCount() {
		return productCount;
	}

	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}

	public long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(long subtotal) {
		this.subtotal = subtotal;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}