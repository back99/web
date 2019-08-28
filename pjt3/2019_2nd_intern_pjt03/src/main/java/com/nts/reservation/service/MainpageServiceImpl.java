package com.nts.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nts.reservation.dto.Category.CategoryDto;
import com.nts.reservation.dto.Category.CategoryResponse;
import com.nts.reservation.dto.Product.ProductDto;
import com.nts.reservation.dto.Product.ProductResponse;
import com.nts.reservation.dto.Promotion.PromotionDto;
import com.nts.reservation.dto.Promotion.PromotionResponse;
import com.nts.reservation.mapper.CategoryMapper;
import com.nts.reservation.mapper.ProductMapper;
import com.nts.reservation.mapper.PromotionMapper;

/**
 * @FileName : MainpageServiceImpl.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@Service
public class MainpageServiceImpl implements MainpageService {

	private final CategoryMapper categoryMapper;
	private final PromotionMapper promotionMapper;
	private final ProductMapper productMapper;

	/**
	 * 생성자 주입
	 * @param promotionDao
	 * @param categoryDao
	 * @param productDao
	 */
	public MainpageServiceImpl(PromotionMapper promotionMapper, CategoryMapper categoryMapper, ProductMapper productMapper) {
		this.promotionMapper = promotionMapper;
		this.categoryMapper = categoryMapper;
		this.productMapper = productMapper;
	}

	/**
	 * @Method Name : get~List
	 * @작성일 : 2019. 7. 29.
	 * @작성자 : "seungbum"
	 * @Method 설명 :api spec에 맞게 resultset을 반환
	 * @return ~ResultSet
	 */
	@Override
	public PromotionResponse getPromotionList() {
		List<PromotionDto> promotionList = promotionMapper.selectPromotionList();

		return new PromotionResponse(promotionList);
	}

	@Override
	public CategoryResponse getCategoryList() {
		List<CategoryDto> categoryList = categoryMapper.selectCategoryList();

		return new CategoryResponse(categoryList);
	}

	@Override
	public ProductResponse getProductListByCategoryId(int start, Integer categoryId) {
		List<ProductDto> productList = productMapper.selectProductListByCategoryId(start, categoryId);
		int productTotalCount = productMapper.selectProductTotalCountByCategoryId(categoryId);

		return new ProductResponse(productTotalCount, productList);
	}
}
