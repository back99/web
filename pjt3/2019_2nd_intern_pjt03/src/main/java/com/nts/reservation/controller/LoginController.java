/**
 * 
 */
package com.nts.reservation.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.service.ReservepageService;

/**
 * @FileName : LoginController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 16.
 * @Author : 정승범
 */
@Controller
public class LoginController {

	private final static int ONE_DAY_LIMIT = 60 * 60 * 24;
	private final ReservepageService reservepageService;

	public LoginController(ReservepageService reservepageService) {
		this.reservepageService = reservepageService;
	}

	/**
	 * @Method Name : requestLogin
	 * @작성일 : 2019. 8. 14.
	 * @작성자 : 정승범
	 * @Method 설명 : 이메일 유효성 검사 후 myreservation OR bookinglogin 페이지로 이동
	 * @param email
	 * @param response
	 * @return
	 */
	@GetMapping("/login")
	public String requestLogin(@RequestParam("resrv_email") String email, HttpServletResponse response) {

		if (reservepageService.getReservationInfoCount(email) > 0) {
			Cookie cookie = new Cookie("email", email);
			cookie.setMaxAge(ONE_DAY_LIMIT);
			cookie.setPath("/");
			response.addCookie(cookie);

			return "myreservation";
		} else {
			return "bookinglogin";
		}
	}

}
