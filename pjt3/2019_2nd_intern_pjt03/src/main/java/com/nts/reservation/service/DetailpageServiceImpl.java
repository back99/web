/**
 * 
 */
package com.nts.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nts.reservation.mapper.CommentMapper;
import com.nts.reservation.mapper.DisplayMapper;
import com.nts.reservation.mapper.ProductMapper;

import com.nts.reservation.dto.Comment.CommentsDto;
import com.nts.reservation.dto.Display.DisplayInfoDto;
import com.nts.reservation.dto.Display.DisplayInfoImageDto;
import com.nts.reservation.dto.Display.DisplayInfoResponse;
import com.nts.reservation.dto.Product.ProductImagesDto;
import com.nts.reservation.dto.Product.ProductPricesDto;

/**
 * 디테일 페이지 에게 정보를 넘겨주는 서비스
 * @FileName : DetailpageServiceImpl.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 2.
 * @Author : 정승범
 */
@Service
public class DetailpageServiceImpl implements DetailpageService {

	private final DisplayMapper displayMapper;
	private final CommentMapper commentMapper;
	private final ProductMapper productMapper;

	/**
	 * 생성자 주입
	 * @param avgScoreDao
	 * @param commentImagesDao
	 * @param commentsDao
	 * @param displayInfoDao
	 * @param displayInfoImageDao
	 * @param productImageDao
	 * @param productPricesDao
	 * @param imageFileNameByEtTypeDao
	 * @param totalCountDao
	 */
	public DetailpageServiceImpl(CommentMapper commentMapper,
		ProductMapper productMapper, DisplayMapper displayMapper) {

		this.commentMapper = commentMapper;
		this.productMapper = productMapper;
		this.displayMapper = displayMapper;
	}

	/**
	 * @Method Name : getDisplayInfoResponseByDisplayInfoId
	 * @작성일 : 2019. 8. 2.
	 * @작성자 : 정승범
	 * @Method 설명 : displayInfo 를 반환해 주는 함수
	 * @param displayInfoId
	 * @return
	 */
	@Override
	public DisplayInfoResponse getDisplayInfoResponseByDisplayInfoId(int displayInfoId, Boolean commentLimitFlag) {
		int totalCount = displayMapper.selectTotalCountByDisplayInfoId(displayInfoId);
		float avgScoreByDisplayInfoId = displayMapper.selectAvgScoreByDisplayInfoId(displayInfoId);
		List<CommentsDto> comments = commentMapper.selectCommentsByDisplayInfoId(displayInfoId, commentLimitFlag);
		DisplayInfoDto displayInfo = displayMapper.selectDisplayInfoByDisplayInfoId(displayInfoId);
		DisplayInfoImageDto displayInfoImage = displayMapper.selectDisplayInfoImagesByDisplayInfoId(displayInfoId);
		List<ProductImagesDto> productImages = productMapper.getProductImagesByDisplayInfoId(displayInfoId);
		List<ProductPricesDto> productPrices = productMapper.getProductPricesByDisplayInfoId(displayInfoId);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse.setTotalCount(totalCount);
		displayInfoResponse.setAverageScore(avgScoreByDisplayInfoId);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage);
		displayInfoResponse.setProductImages(productImages);
		displayInfoResponse.setProductPrices(productPrices);

		return displayInfoResponse;
	}

	/**
	 * @Method Name : getEtImageResponseByDisplayInfoId
	 * @작성일 : 2019. 8. 5.
	 * @작성자 : 정승범
	 * @Method 설명 : etSaveFileName 첫번째것을 반환해주는 함수
	 * @param displayInfoId
	 * @return
	 */
	@Override
	public String getEtImageResponseByDisplayInfoId(int displayInfoId) {
		try {
			return displayMapper.selectEtIamgeFileName(displayInfoId);
		} catch (NullPointerException e) {
			return "NotValue";
		}
	}

}
