/**
 * 
 */
package com.nts.reservation.dto.Reservation;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @FileName : ReservationInfo.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
@Data
public class ReservationInfo {

	private int productId;
	private int reservationInfoId;
	private String title;
	private String openingHour;
	private String placeStreet;
	private String placeName;
	private boolean cancelFlag;
	private long totalPrice;
	private Date reservationDate;
	private String reservationStringDate;
	private List<PriceCount> priceCount;

}
