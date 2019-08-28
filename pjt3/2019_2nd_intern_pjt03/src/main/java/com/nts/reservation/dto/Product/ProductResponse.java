package com.nts.reservation.dto.Product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @FileName : ProductDtoResultSet.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 29.
 * @Author : "seungbum"
 */
@Data
@AllArgsConstructor
public class ProductResponse {

	private int totalCount;
	private List<ProductDto> items;

}
