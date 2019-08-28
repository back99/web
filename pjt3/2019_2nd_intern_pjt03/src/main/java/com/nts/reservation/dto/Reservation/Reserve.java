/**
 * 
 */
package com.nts.reservation.dto.Reservation;

import lombok.Data;

/**
 * @FileName : reserveResponse.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */

@Data
public class Reserve {

	private String priceTypeName;
	private int productPriceId;
	private int price;
	private float discountRate;
	private int reservationInfoId;

}
