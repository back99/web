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
public class ReserveResponse {

	private int productId;
	private String title;
	private String reserveDate;
	private String saveFileName;
	private String placeName;
	private String openingHours;
	private List<Reserve> reserve;

}
