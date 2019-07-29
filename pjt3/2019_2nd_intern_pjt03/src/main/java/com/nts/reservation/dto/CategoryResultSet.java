/**
 * 
 */
package com.nts.reservation.dto;

import java.util.List;

/**
 * @FileName : CategoryResultSet.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 29.
 * @Author : "seungbum"
 */
public class CategoryResultSet {

	private List<CategoryDto> items;

	public CategoryResultSet(List<CategoryDto> items) {
		this.items = items;
	}

	public List<CategoryDto> getItems() {
		return items;
	}

	public void setItems(List<CategoryDto> items) {
		this.items = items;
	}

}
