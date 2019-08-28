/**
 * 
 */
package com.nts.reservation.dto.Comment;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName : CommentParam.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 22.
 * @Author : 정승범
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentParam {

	private MultipartFile imageFile;
	private String comment;
	private int reservationInfoId;
	private int score;
	private int productId;
	private int id;

}
