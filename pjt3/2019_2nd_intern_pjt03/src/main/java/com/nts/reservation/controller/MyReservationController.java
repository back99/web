/**
 * 
 */
package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FileName : MyReservationController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 16.
 * @Author : 정승범
 */
@Controller
public class MyReservationController {

	/**
	 * @Method Name : requestMyReservation
	 * @작성일 : 2019. 8. 14.
	 * @작성자 : 정승범
	 * @Method 설명 : myreservation 페이지로 move
	 * @return
	 */
	@GetMapping("/myreservation")
	public String requestMyReservation() {
		return "myreservation";
	}

}
