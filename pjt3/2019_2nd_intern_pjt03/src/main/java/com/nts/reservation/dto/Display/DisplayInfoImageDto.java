/**
 * 
 */
package com.nts.reservation.dto.Display;

import lombok.Data;

/**
 * @FileName : displayInfoImage.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 30.
 * @Author : 정승범
 */
@Data
public class DisplayInfoImageDto {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private int displayInfoId;
	private int displayInfoImageId;
	private int fileId;
	private String fileName;
	private String modifyDate;
	private String saveFileName;

}
