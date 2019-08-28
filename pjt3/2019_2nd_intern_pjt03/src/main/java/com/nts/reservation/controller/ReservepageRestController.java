/**
 * 
 */
package com.nts.reservation.controller;

import java.io.InvalidObjectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Reservation.ReservationInfoResponse;
import com.nts.reservation.dto.Reservation.ReservationParam;
import com.nts.reservation.dto.Reservation.ReservationPrice;
import com.nts.reservation.dto.Reservation.ReserveResponse;
import com.nts.reservation.service.ReservepageService;

/**
 * @FileName : ReservationRestController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 21.
 * @Author : 정승범
 */
@RestController
public class ReservepageRestController {

	private static final String EMAIL_VALIDATOR = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
	private static final String TEL_VALIDATOR = "^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$\r\n";
	private static final Pattern emailPattern = Pattern.compile(EMAIL_VALIDATOR);
	private static final Pattern telPattern = Pattern.compile(TEL_VALIDATOR);

	private final ReservepageService reservepageService;

	public ReservepageRestController(ReservepageService reservepageService) {
		this.reservepageService = reservepageService;
	}

	/**
	 * @Method Name : requestReserve
	 * @작성일 : 2019. 8. 13.
	 * @작성자 : 정승범
	 * @Method 설명 : reserve 페이지 보여주기 위한 API
	 * @param displayInfoId
	 * @return
	 */
	@GetMapping("/api/reserve")
	public ReserveResponse requestReserve(@RequestParam("displayInfoId") int displayInfoId) {
		ReserveResponse reserveResponse = reservepageService.getReserveResponseByDisplayInfoId(displayInfoId);

		return reserveResponse;
	}

	/**
	 * @Method Name : requestReservationParam
	 * @작성일 : 2019. 8. 13.
	 * @작성자 : 정승범
	 * @Method 설명 : 예매확인 버튼 누르시 데이터 넣기
	 * @param reservationParam
	 * @throws InvalidObjectException 
	 */
	@PostMapping("/api/reservation")
	public void requestReservationParam(@RequestBody ReservationParam reservationParam) throws InvalidObjectException {
		Matcher emailMatcher = emailPattern.matcher(reservationParam.getReservationEmail());
		Matcher telMatcher = telPattern.matcher(reservationParam.getReservationTelephone());

		for (ReservationPrice prices : reservationParam.getPrices()) {
			if (prices.getCount() == 0) {
				throw new InvalidObjectException("갯수가 잘못되었습니다.");
			}
		}

		if (!emailMatcher.find() && !telMatcher.find()) {
			throw new InvalidObjectException("전화번호 및 email 입력이 잘못 되었습니다.");
		} else if (!emailMatcher.find()) {
			throw new InvalidObjectException("email 입력이 잘못 되었습니다.");
		} else if (!telMatcher.find()) {
			throw new InvalidObjectException("전화번호 입력이 잘못 되었습니다.");
		}

		reservepageService.addReservationParam(reservationParam);
	}

	/**
	 * @Method Name : requestMyreservation
	 * @작성일 : 2019. 8. 16.
	 * @작성자 : 정승범
	 * @Method 설명 : 나의예약정보 데이터 가져오기
	 * @param email
	 * @return
	 * @throws InvalidObjectException 
	 */
	@GetMapping("/api/myreservation")
	public ReservationInfoResponse requestMyreservation(@CookieValue("email") Cookie cookie) {
		String email = cookie.getValue();
		ReservationInfoResponse reservationInfoResponse = reservepageService.getReservationInfoResponseByEmail(email);

		return reservationInfoResponse;
	}

	/**
	 * @Method Name : updateReservation
	 * @작성일 : 2019. 8. 19.
	 * @작성자 : 정승범
	 * @Method 설명 : cancelflag 업데이트
	 * @param reservationInfoId
	 */
	@GetMapping("/api/updateReservation")
	public void updateReservation(@RequestParam("reservationInfoId") int reservationInfoId) {
		reservepageService.updateReservationByReservationInfoId(reservationInfoId);
	}

}
