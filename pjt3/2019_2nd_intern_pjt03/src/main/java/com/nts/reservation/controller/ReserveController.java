/**
 * 
 */
package com.nts.reservation.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FileName : ReserveController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 16.
 * @Author : 정승범
 */
@Controller
public class ReserveController {

	/**
	 * @Method Name : requestReview
	 * @작성일 : 2019. 8. 6.
	 * @작성자 : 정승범
	 * @Method 설명 : reserve 페이지로 move
	 * @return
	 */
	@GetMapping("/reserve")
	public String requestreserve(@RequestParam("displayInfoId") int displayInfoId, Map reserveResponseMap) {
		reserveResponseMap.put("dispalyInfoId", displayInfoId);

		return "reserve";
	}

}
