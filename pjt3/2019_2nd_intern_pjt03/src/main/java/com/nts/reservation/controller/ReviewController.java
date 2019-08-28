/**
 * 
 */
package com.nts.reservation.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FileName : ReviewPageController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 16.
 * @Author : 정승범
 */
@Controller
public class ReviewController {

	/**
	 * @Method Name : requestReview
	 * @작성일 : 2019. 8. 2.
	 * @작성자 : 정승범
	 * @Method 설명 : 리뷰 페이지로 displayInfoId 를 받아 넘기는 함수
	 * @param dispalyInfoId
	 * @param detailResponseMap
	 * @return
	 */
	@GetMapping("/review")
	public String requestReview(@RequestParam("displayInfoId") int displayInfoId, Map detailResponseMap) {
		detailResponseMap.put("dispalyInfoId", displayInfoId);

		return "review";
	}

}
