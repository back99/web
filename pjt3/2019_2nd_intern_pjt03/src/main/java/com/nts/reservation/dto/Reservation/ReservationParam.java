/**
 * 
 */
package com.nts.reservation.dto.Reservation;

import java.util.List;

import lombok.Data;

/**
 * @FileName : ReserveResponse.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */

@Data
public class ReservationParam {

	private int displayInfoId;
	private List<ReservationPrice> prices;
	private int productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private String reservationYearMonthDay;
	private int id;

}
