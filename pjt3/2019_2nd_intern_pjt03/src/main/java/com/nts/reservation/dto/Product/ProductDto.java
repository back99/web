package com.nts.reservation.dto.Product;

import lombok.Data;

/**
 * @FileName : ProductDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@Data
public class ProductDto {

	private int displayInfoId;
	private int productId;
	private String productDescription;
	private String placeName;
	private String productContent;
	private String productImageUrl;

}
