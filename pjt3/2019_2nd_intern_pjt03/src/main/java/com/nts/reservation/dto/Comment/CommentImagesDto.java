/**
 * 
 */
package com.nts.reservation.dto.Comment;

import lombok.Data;

/**
 * @FileName : CommentImages.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 30.
 * @Author : 정승범
 */
@Data
public class CommentImagesDto {
	
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private int fileId;
	private String fileName;
	private int imageId;
	private String modifyDate;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private String saveFileName;

}
