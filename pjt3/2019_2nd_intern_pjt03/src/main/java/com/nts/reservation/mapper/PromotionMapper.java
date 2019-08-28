/**
 * 
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Promotion.PromotionDto;

/**
 * @FileName : PromotionMapper.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
@Repository
public interface PromotionMapper {

	public List<PromotionDto> selectPromotionList();
	
}
