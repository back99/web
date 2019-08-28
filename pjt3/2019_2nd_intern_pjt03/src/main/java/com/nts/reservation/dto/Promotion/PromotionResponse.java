package com.nts.reservation.dto.Promotion;

import java.util.List;

import lombok.Data;

/**
 * @FileName : MapperPromotionDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 26.
 * @Author : "seungbum"
 */
@Data
public class PromotionResponse {

	public PromotionResponse(List<PromotionDto> items) {
		this.items = items;
	}

	private List<PromotionDto> items;

}
