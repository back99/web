/**
 * 
 */
package com.nts.reservation.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FileName : ReviewWriteContoller.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 20.
 * @Author : 정승범
 */
@Controller
public class ReviewWriteContoller {

	/**
	 * @Method Name : requestReviewWrite
	 * @작성일 : 2019. 8. 26.
	 * @작성자 : 정승범
	 * @Method 설명 : 코멘트 페이지로 이동
	 * @param reservationInfoId
	 * @param title
	 * @param reveiwResponseMap
	 * @return
	 */
	@GetMapping("/reviewWrite")
	public String requestReviewWrite(@RequestParam("reservationInfoId") int reservationInfoId, @RequestParam("title") String title, Map reveiwResponseMap) {
		reveiwResponseMap.put("reservationInfoId", reservationInfoId);
		reveiwResponseMap.put("title", title);

		return "reviewWrite";
	}
}
