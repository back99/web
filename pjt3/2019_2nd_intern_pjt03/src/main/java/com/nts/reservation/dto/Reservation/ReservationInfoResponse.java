/**
 * 
 */
package com.nts.reservation.dto.Reservation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @FileName : ReservationInfoResponse.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */

@Data
@AllArgsConstructor
public class ReservationInfoResponse {

	private List<ReservationInfo> reservations;
	private int size;

}
