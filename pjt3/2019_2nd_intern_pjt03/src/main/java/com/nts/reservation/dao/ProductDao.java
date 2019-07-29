/**
 * 
 */
package com.nts.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ProductDto;

/**
 * @FileName : ProductDao.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@Repository
public class ProductDao {

	public static final String SELECT_PRODUCT_BYID = 
		"SELECT  product.category_id AS displayInfoId , " +
			"product.category_id AS productId , " +
			"save_file_name AS productImageUrl, " +
			"product.description AS productDescription, " +
			"display_info.place_name AS placeName ," +
			"product.content AS productContent " +
		"FROM product, file_info , display_info , " +
			"(SELECT product_id, file_id FROM product_image WHERE type = 'th') AS type " +
		"WHERE file_info.id=type.file_id " +
			"AND type.product_id = product.id " +
			"AND display_info.product_id=type.product_id " +
			"AND product.category_id=:categoryId " +
		"LIMIT :start , 4";

	public static final String SELECT_PRODUCT_ALL = 
		"SELECT  product.category_id AS displayInfoId , " +
			"product.category_id AS productId , " +
			"save_file_name AS productImageUrl, " +
			"product.description AS productDescription, " +
			"display_info.place_name AS placeName ," +
			"product.content AS productContent " +
		"FROM product, file_info , display_info , " +
			"(SELECT product_id, file_id FROM product_image WHERE type = 'th') AS type " +
		"WHERE file_info.id=type.file_id " +
			"AND type.product_id = product.id " +
			"AND display_info.product_id=type.product_id " +
		"LIMIT :start , 4";

	public static final String SELECT_PRODUCT_TOTAL_COUNT_BYID = 
		"SELECT  COUNT(*) AS totalCount " +
		"FROM product, file_info , display_info , " +
			"(SELECT product_id, file_id FROM product_image WHERE type = 'th') AS type " +
		"WHERE file_info.id=type.file_id " +
			"AND type.product_id = product.id " +
			"AND display_info.product_id=type.product_id " +
			"AND product.category_id=:categoryId";

	public static final String SELECT_PRODUCT_TOTAL_COUNT_ALL = 
		"SELECT  COUNT(*) AS totalCount " +
		"FROM product, file_info , display_info , " +
			"(SELECT product_id, file_id FROM product_image WHERE type = 'th') AS type " +
		"WHERE file_info.id=type.file_id " +
			"AND type.product_id = product.id " +
			"AND display_info.product_id=type.product_id ";

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper;

	public ProductDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);
	}

	/**
	 * @Method Name : selectProductList
	 * @작성일 : 2019. 7. 29.
	 * @작성자 : "seungbum"
	 * @Method 설명 : LIMIT start, 4 AND product.category_id=categoryId에 맞는 product 쿼리
	 * @param start
	 * @param categoryId
	 * @return
	 */
	public List<ProductDto> selectProductListByCategoryId(int start, int categoryId) {
		Map<String, Integer> params = new HashMap<>();

		if (categoryId == 0) {
			params.put("start", start);
			return jdbc.query(SELECT_PRODUCT_ALL, params, rowMapper);
		} else {
			params.put("start", start);
			params.put("categoryId", categoryId);
			return jdbc.query(SELECT_PRODUCT_BYID, params, rowMapper);
		}
	}

	/**
	 * @Method Name : selectProductTotalCount
	 * @작성일 : 2019. 7. 29.
	 * @작성자 : "seungbum"
	 * @Method 설명 : 카테고리별 카운트 해주는 쿼리
	 * @param categoryId
	 * @return
	 */
	public int selectProductTotalCountByCategoryId(int categoryId) {
		Map<String, Integer> params = new HashMap<>();

		if (categoryId == 0) {
			return jdbc.queryForObject(SELECT_PRODUCT_TOTAL_COUNT_ALL, params, Integer.class);
		} else {
			params.put("categoryId", categoryId);
			return jdbc.queryForObject(SELECT_PRODUCT_TOTAL_COUNT_BYID, params, Integer.class);
		}

	}

}
