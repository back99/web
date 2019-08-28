/**
 * 
 */
package com.nts.reservation.controller;

import java.io.InvalidObjectException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FileName : MainController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 16.
 * @Author : 정승범
 */
@Controller
public class MainController {

	/**
	 * @Method Name : requestMain
	 * @작성일 : 2019. 8. 16.
	 * @작성자 : 정승범
	 * @Method 설명 : 메인페이지로 이동
	 * @return
	 * @throws InvalidObjectException 
	 */
	@GetMapping("/mainpage")
	public String requestMain() {
		return "mainpage";
	}

}
