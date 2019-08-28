/**
 * 
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Product.ProductDto;
import com.nts.reservation.dto.Product.ProductImagesDto;
import com.nts.reservation.dto.Product.ProductPricesDto;

/**
 * @FileName : ProductMapper.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
@Repository
public interface ProductMapper {

	public List<ProductDto> selectProductListByCategoryId(@Param("start") int start, @Param("categoryId") Integer categoryId);

	public int selectProductTotalCountByCategoryId(@Param("categoryId") Integer categoryId);

	public List<ProductImagesDto> getProductImagesByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

	public List<ProductPricesDto> getProductPricesByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

}
