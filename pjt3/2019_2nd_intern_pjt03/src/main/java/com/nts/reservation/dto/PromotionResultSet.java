package com.nts.reservation.dto;

import java.util.List;

/**
 * @FileName : MapperPromotionDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 26.
 * @Author : "seungbum"
 */
public class PromotionResultSet {

	private List<PromotionDto> items;

	public PromotionResultSet(List<PromotionDto> items) {
		this.items = items;
	}

	public List<PromotionDto> getItems() {
		return items;
	}

	public void setItems(List<PromotionDto> items) {
		this.items = items;
	}

}
