package com.nts.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.CategoryDto;
import com.nts.reservation.dto.CategoryResultSet;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.ProductResultSet;
import com.nts.reservation.dto.PromotionDto;
import com.nts.reservation.dto.PromotionResultSet;

/**
 * @FileName : MainpageServiceImpl.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@Service
public class MainpageServiceImpl implements MainpageService {

	private final PromotionDao promotionDao;
	private final CategoryDao categoryDao;
	private final ProductDao productDao;

	/**
	 * @param promotionDao
	 * @param categoryDao
	 * @param productDao
	 * 생성자 주입
	 */
	@Autowired
	public MainpageServiceImpl(PromotionDao promotionDao, CategoryDao categoryDao, ProductDao productDao) {
		this.promotionDao = promotionDao;
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}

	/**
	 * @Method Name : get~List
	 * @작성일 : 2019. 7. 29.
	 * @작성자 : "seungbum"
	 * @Method 설명 :api spec에 맞게 resultset을 반환
	 * @return ~ResultSet
	 */
	@Override
	public PromotionResultSet getPromotionList() {
		List<PromotionDto> promotionList = promotionDao.selectPromotionList();

		return new PromotionResultSet(promotionList);
	}

	@Override
	public CategoryResultSet getCategoryList() {
		List<CategoryDto> categoryList = categoryDao.selectCategoryList();

		return new CategoryResultSet(categoryList);
	}

	@Override
	public ProductResultSet getProductListByCategoryId(int start, int categoryId) {
		List<ProductDto> productList = productDao.selectProductListByCategoryId(start, categoryId);
		int productTotalCount = productDao.selectProductTotalCountByCategoryId(categoryId);

		return new ProductResultSet(productTotalCount, productList);
	}
}
