/**
 * 
 */
package com.nts.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Comment.CommentIdDto;
import com.nts.reservation.dto.Comment.CommentParam;
import com.nts.reservation.dto.Comment.FileInfo;
import com.nts.reservation.mapper.CommentMapper;

/**
 * @FileName : CommentpageServiceImpl.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 22.
 * @Author : 정승범
 */
@Service
public class CommentpageServiceImpl implements CommentpageService {

	private final CommentMapper commentMapper;

	public CommentpageServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	/**
	 * @Method Name : addCommentImage
	 * @작성일 : 2019. 8. 22.
	 * @작성자 : 정승범
	 * @Method 설명 : 이미지, comment, id 디비에 저장
	 * @param commentParam
	 */
	@Override
	@Transactional
	public void addCommentImage(CommentParam commentParam, FileInfo fileInfo) {
		commentParam.setProductId(commentMapper.selectProductId(commentParam.getReservationInfoId()));
		commentMapper.insertCommentParam(commentParam);
		commentMapper.insertFileInfo(fileInfo);

		CommentIdDto commentIdDto = new CommentIdDto();
		commentIdDto.setFileId(fileInfo.getId());
		commentIdDto.setReservationUserCommentId(commentParam.getId());
		commentIdDto.setReservationInfoId(commentParam.getReservationInfoId());
		commentMapper.insertCommentImage(commentIdDto);
	}

	/**
	 * @Method Name : getCommentImageFileSavePath
	 * @작성일 : 2019. 8. 27.
	 * @작성자 : 정승범
	 * @Method 설명 : 이미지 저장경로 가져오기
	 * @param commentId
	 * @return
	 */
	@Override
	public String getCommentImageFileSavePath(int fileId) {
		return commentMapper.selectCommentImageFileSavePath(fileId);
	}

}
