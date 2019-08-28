/**
 * 
 */
package com.nts.reservation.dto.Reservation;

import java.util.List;

import lombok.Data;

/**
 * @FileName : ReservationResponse.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */

@Data
public class ReservationResponse {

	private boolean cancelYn;
	private String createDate;
	private int displayInfoId;
	private String modifyDate;
	private List<ReservationPrice> prices;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;

}
