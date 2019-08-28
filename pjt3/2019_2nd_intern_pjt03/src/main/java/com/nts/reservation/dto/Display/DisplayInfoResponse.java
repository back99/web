/**
 * 
 */
package com.nts.reservation.dto.Display;

import java.util.List;

import com.nts.reservation.dto.Comment.CommentsDto;
import com.nts.reservation.dto.Product.ProductImagesDto;
import com.nts.reservation.dto.Product.ProductPricesDto;

import lombok.Data;

/**
 * @FileName : DetailProductsDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 30.
 * @Author : 정승범
 */
@Data
public class DisplayInfoResponse {
	private int totalCount;
	private float averageScore;
	private List<CommentsDto> comments;
	private DisplayInfoDto displayInfo;
	private DisplayInfoImageDto displayInfoImage;
	private List<ProductImagesDto> productImages;
	private List<ProductPricesDto> productPrices;

}
