/**
 * 
 */
package com.nts.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Display.DisplayInfoResponse;
import com.nts.reservation.service.DetailpageService;

/**
 * @FileName : DetailpageRestController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 21.
 * @Author : 정승범
 */
@RestController
public class DetailpageRestController {

	private final DetailpageService detailpageService;

	public DetailpageRestController(DetailpageService detailpageService) {
		this.detailpageService = detailpageService;
	}

	/**
	 * @Method Name : requestDisplayInfo
	 * @작성일 : 2019. 8. 2.
	 * @작성자 : 정승범
	 * @Method 설명 : 프로덕트API by displayInfoId
	 * @param displayInfoId
	 * @return
	 */
	@GetMapping("/api/products/{displayInfoId}")
	public DisplayInfoResponse requestDisplayInfo(@PathVariable int displayInfoId, @RequestParam(name = "commentLimitFlag", required = false) Boolean commentLimitFlag) {
		DisplayInfoResponse displayInfoResponse = detailpageService.getDisplayInfoResponseByDisplayInfoId(displayInfoId, commentLimitFlag);

		return displayInfoResponse;
	}

	/**
	 * @Method Name : requestEtImage
	 * @작성일 : 2019. 8. 5.
	 * @작성자 : 정승범
	 * @Method 설명 :etImageAPI
	 * @param displayInfoId
	 * @return
	 */
	@GetMapping("/api/image/et")
	public String requestEtImage(@RequestParam("displayInfoId") int displayInfoId) {
		String imageFileNameResponse = detailpageService.getEtImageResponseByDisplayInfoId(displayInfoId);

		return imageFileNameResponse;
	}

}
