/**
 * 
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Comment.CommentIdDto;
import com.nts.reservation.dto.Comment.CommentImagesDto;
import com.nts.reservation.dto.Comment.CommentParam;
import com.nts.reservation.dto.Comment.CommentsDto;
import com.nts.reservation.dto.Comment.FileInfo;

/**
 * @FileName : CommentMapper.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 8.
 * @Author : 정승범
 */
@Repository
public interface CommentMapper {

	public List<CommentsDto> selectCommentsByDisplayInfoId(@Param("displayInfoId") int displayInfoId, @Param("commentLimitFlag") Boolean commentLimitFlag);

	public List<CommentImagesDto> selectCommentImagesByDisplayInfoId(@Param("commentId") int commentId);

	public void insertCommentParam(@Param("commentParam") CommentParam commentParam);

	public int selectProductId(@Param("reservationInfoId") int reservationInfoId);

	public void insertFileInfo(@Param("fileInfo") FileInfo fileInfo);

	public void insertCommentImage(@Param("commentIdDto") CommentIdDto commentIdDto);

	public String selectCommentImageFileSavePath(@Param("fileId") int fileId);

}
