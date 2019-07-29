package com.nts.reservation.dto;

/**
 * @FileName : PromotionDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
public class PromotionDto {

	private int id;
	private int productId;
	private String promotionUrl;

	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
