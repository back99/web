package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.CategoryResultSet;
import com.nts.reservation.dto.ProductResultSet;
import com.nts.reservation.dto.PromotionResultSet;
import com.nts.reservation.service.MainpageService;

/**
 * @FileName : MainpageController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@RestController
public class MainpageController {

	private final MainpageService mainpageService;

	@Autowired
	public MainpageController(MainpageService mainpageService) {
		this.mainpageService = mainpageService;
	}

	@GetMapping("/api/promotions")
	public PromotionResultSet requestPromotion() {
		PromotionResultSet promotionResultSet = mainpageService.getPromotionList();

		return promotionResultSet;
	}

	@GetMapping("/api/categories")
	public CategoryResultSet requestCategory() {
		CategoryResultSet categoryResultSet = mainpageService.getCategoryList();

		return categoryResultSet;
	}

	@GetMapping("/api/products")
	public ProductResultSet requestProduct(@RequestParam("start") int start, 
										   @RequestParam(name = "categoryId" ,required = false, defaultValue = "0") int categoryId) {
		ProductResultSet productResultSet = mainpageService.getProductListByCategoryId(start, categoryId);

		return productResultSet;
	}

}
