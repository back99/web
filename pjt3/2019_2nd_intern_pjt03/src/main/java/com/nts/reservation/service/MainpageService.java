package com.nts.reservation.service;

import com.nts.reservation.dto.CategoryResultSet;
import com.nts.reservation.dto.ProductResultSet;
import com.nts.reservation.dto.PromotionResultSet;

/**
 * @FileName : MainpageService.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
public interface MainpageService {

	public PromotionResultSet getPromotionList();

	public CategoryResultSet getCategoryList();

	public ProductResultSet getProductListByCategoryId(int start, int categoryId);

}
