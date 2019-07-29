package com.nts.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.PromotionDto;

/**
 * @FileName : PromotionDao.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 25.
 * @Author : "seungbum"
 */
@Repository
public class PromotionDao {
	public static final String SELECT_PROMOTION = 
		"SELECT promotion.id AS id, type.product_id  AS productId,save_file_name AS promotionUrl " + 
	    "FROM promotion,(SELECT product_id, file_id FROM product_image WHERE type = 'th') "	+ 
		"AS type , file_info " + 
	    "WHERE file_info.id=type.file_id AND type.product_id = promotion.product_id";

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<PromotionDto> rowMapper;

	public PromotionDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		rowMapper = BeanPropertyRowMapper.newInstance(PromotionDto.class);
	}

	
	/**
	 * @Method Name : selectPromotionList
	 * @작성일 : 2019. 7. 29.
	 * @작성자 : "seungbum"
	 * @Method 설명 : 프로모션 imageurl 을 가지고 위한 쿼리
	 * @return
	 */
	public List<PromotionDto> selectPromotionList() {
		

		return jdbc.query(SELECT_PROMOTION, rowMapper);
	}

}
