/**
 * 
 */
package com.nts.reservation.dto.Comment;

import lombok.Data;

/**
 * @FileName : CommentIdDto.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 23.
 * @Author : 정승범
 */
@Data
public class CommentIdDto {

	private int fileId;
	private int reservationUserCommentId;
	private int reservationInfoId;

}
