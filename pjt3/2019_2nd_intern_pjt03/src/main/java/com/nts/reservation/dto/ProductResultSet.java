package com.nts.reservation.dto;

import java.util.List;

/**
 * @FileName : ProductDtoResultSet.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 29.
 * @Author : "seungbum"
 */
public class ProductResultSet {

	private int totalCount;
	private List<ProductDto> items;

	public ProductResultSet(int totalCount, List<ProductDto> items) {
		this.totalCount = totalCount;
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ProductDto> getItems() {
		return items;
	}

	public void setItems(List<ProductDto> items) {
		this.items = items;
	}

}
