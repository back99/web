/**
 * 
 */
package com.nts.reservation.dto.Comment;

import java.util.List;

import lombok.Data;

/**s
 * @FileName : CommentsDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 30.
 * @Author : 정승범
 */
@Data
public class CommentsDto {
	
	private int commentId;
	private String comment;
	private String createDate;
	private String modifyDate;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private float score;
	private List<CommentImagesDto> commentImages;

}
