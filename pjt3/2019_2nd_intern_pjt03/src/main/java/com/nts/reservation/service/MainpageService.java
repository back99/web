package com.nts.reservation.service;

import com.nts.reservation.dto.Category.CategoryResponse;
import com.nts.reservation.dto.Product.ProductResponse;
import com.nts.reservation.dto.Promotion.PromotionResponse;

/**
 * @FileName : MainpageService.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
public interface MainpageService {

	public PromotionResponse getPromotionList();

	public CategoryResponse getCategoryList();

	public ProductResponse getProductListByCategoryId(int start, Integer categoryId);

}
