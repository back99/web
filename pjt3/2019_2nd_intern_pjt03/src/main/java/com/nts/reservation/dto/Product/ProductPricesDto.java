/**
 * 
 */
package com.nts.reservation.dto.Product;

import lombok.Data;

/**
 * @FileName : productPrices.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 30.
 * @Author : 정승범
 */
@Data
public class ProductPricesDto {
	private String createDate;
	private int discountRate;
	private String modifyDate;
	private int price;
	private String priceTypeName;
	private int productId;
	private int productPriceId;

}
