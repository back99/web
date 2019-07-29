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

import com.nts.reservation.dto.CategoryDto;

/**
 * @FileName : CategoryDao.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@Repository
public class CategoryDao {
	public static final String SELECT_CATEGORY = 
		"SELECT COUNT(display_info.id) AS count, category_id AS id, name " +
		"FROM product , category ,display_info " +
		"WHERE product.category_id=category.id " +
		"AND display_info.product_id=product.id " +
		"GROUP BY category_id";

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CategoryDto> rowMapper;

	public CategoryDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);
	}

	/**
	 * @Method Name : selectCategoryList
	 * @작성일 : 2019. 7. 29.
	 * @작성자 : "seungbum"
	 * @Method 설명 : 카테고리별 count 를 가져오기 위한 쿼리
	 * @return
	 */
	public List<CategoryDto> selectCategoryList() {

		return jdbc.query(SELECT_CATEGORY, rowMapper);
	}

}
