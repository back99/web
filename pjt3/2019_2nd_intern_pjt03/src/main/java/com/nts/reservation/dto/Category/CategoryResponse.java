/**
 * 
 */
package com.nts.reservation.dto.Category;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @FileName : CategoryResultSet.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 29.
 * @Author : "seungbum"
 */
@Data
@AllArgsConstructor
public class CategoryResponse {
	
	private List<CategoryDto> items;

}
