/**
 * 
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.Comment.CommentParam;
import com.nts.reservation.dto.Comment.FileInfo;

/**
 * @FileName : CommentpageService.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 22.
 * @Author : 정승범
 */
public interface CommentpageService {

	public void addCommentImage(CommentParam commentParam, FileInfo fileInfo);

	public String getCommentImageFileSavePath(int fileId);

}
