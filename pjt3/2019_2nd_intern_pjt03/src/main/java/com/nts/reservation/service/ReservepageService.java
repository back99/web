/**
 * 
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.Reservation.ReservationInfoResponse;
import com.nts.reservation.dto.Reservation.ReservationParam;
import com.nts.reservation.dto.Reservation.ReserveResponse;

/**
 * @FileName : ReservepageService.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
public interface ReservepageService {

	public ReserveResponse getReserveResponseByDisplayInfoId(int displayInfoId);

	public void addReservationParam(ReservationParam reservationParam);

	public ReservationInfoResponse getReservationInfoResponseByEmail(String email);

	public void updateReservationByReservationInfoId(int reservationInfoId);

	public int getReservationInfoCount(String email);

}
