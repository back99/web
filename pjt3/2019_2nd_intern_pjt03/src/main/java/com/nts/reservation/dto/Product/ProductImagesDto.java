package com.nts.reservation.dto.Product;

import lombok.Data;

/**
 * @FileName : productImages.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 30.
 * @Author : 정승범
 */
@Data
public class ProductImagesDto {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private int fileInfoId;
	private String fileName;
	private String modifyDate;
	private int productId;
	private int productImageId;
	private String saveFileName;
	private String type;

}
