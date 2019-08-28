/**
 * 
 */
package com.nts.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Category.CategoryResponse;
import com.nts.reservation.dto.Product.ProductResponse;
import com.nts.reservation.dto.Promotion.PromotionResponse;
import com.nts.reservation.service.MainpageService;

/**
 * @FileName : ProductRestController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 21.
 * @Author : 정승범
 */
@RestController
public class MainpageRestController {

	private final MainpageService mainpageService;

	public MainpageRestController(MainpageService mainpageService) {
		this.mainpageService = mainpageService;
	}

	/**
	 * @Method Name : requestProduct
	 * @작성일 : 2019. 8. 2.
	 * @작성자 : 정승범
	 * @Method 설명 : 프로덕트API
	 * @param start
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/api/products")
	public ProductResponse requestProduct(@RequestParam("start") int start,
		@RequestParam(name = "categoryId", required = false) Integer categoryId) {
		ProductResponse productResponse = mainpageService.getProductListByCategoryId(start, categoryId);

		return productResponse;
	}

	/**
	 * @Method Name : requestPromotion
	 * @작성일 : 2019. 8. 2.
	 * @작성자 : 정승범
	 * @Method 설명 : 프로모션API
	 * @return
	 */
	@GetMapping("/api/promotions")
	public PromotionResponse requestPromotion() {
		PromotionResponse promotionResponse = mainpageService.getPromotionList();

		return promotionResponse;
	}

	/**
	 * @Method Name : requestCategory
	 * @작성일 : 2019. 8. 2.
	 * @작성자 : 정승범
	 * @Method 설명 : 카테고리API
	 * @return
	 */
	@GetMapping("/api/categories")
	public CategoryResponse requestCategory() {
		CategoryResponse categoryResponse = mainpageService.getCategoryList();

		return categoryResponse;
	}

}
