/**
 * 
 */
package com.nts.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.Reservation.ReservationInfo;
import com.nts.reservation.dto.Reservation.ReservationInfoResponse;
import com.nts.reservation.dto.Reservation.ReservationParam;
import com.nts.reservation.dto.Reservation.ReservationPrice;
import com.nts.reservation.dto.Reservation.ReserveResponse;
import com.nts.reservation.mapper.ReservationMapper;

/**
 * @FileName : ReservepageServiceImpl.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
@Service
public class ReservepageServiceImpl implements ReservepageService {

	private final ReservationMapper reservationMapper;

	public ReservepageServiceImpl(ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}

	/**
	 * @Method Name : getReserveResponseByDisplayInfoId
	 * @작성일 : 2019. 8. 16.
	 * @작성자 : 정승범
	 * @Method 설명 : reserveResponse 데이터 가져오기
	 * @param displayInfoId
	 * @return
	 */
	@Override
	public ReserveResponse getReserveResponseByDisplayInfoId(int displayInfoId) {
		ReserveResponse reserveResponse = reservationMapper.selectReserveResponseByDisplayInfoId(displayInfoId);

		return reserveResponse;
	}

	/**
	 * @Method Name : setReservationParam
	 * @작성일 : 2019. 8. 16.
	 * @작성자 : 정승범
	 * @Method 설명 : reservationParam , reservationPrices 데이터 넣기
	 * @param reservationParam
	 */
	@Override
	@Transactional
	public void addReservationParam(ReservationParam reservationParam) {
		reservationMapper.insertReservationInfo(reservationParam);

		int priceInfoId = reservationParam.getId();

		for (ReservationPrice prices : reservationParam.getPrices()) {
			prices.setPriceInfoId(priceInfoId);
		}

		reservationMapper.insertPricesInfo(reservationParam.getPrices());
	}

	/**
	 * @Method Name : getReservationInfoResponseByEmail
	 * @작성일 : 2019. 8. 16.
	 * @작성자 : 정승범
	 * @Method 설명 : reservationInfoResponse 데이터 가져오기
	 * @param email
	 * @return
	 */
	@Override
	public ReservationInfoResponse getReservationInfoResponseByEmail(String email) {
		List<ReservationInfo> reservationInfo = reservationMapper.selectReserveInfoByEmail(email);

		return new ReservationInfoResponse(reservationInfo, reservationInfo.size());
	}

	/**
	 * @Method Name : updateReservationByReservationInfoId
	 * @작성일 : 2019. 8. 19.
	 * @작성자 : 정승범
	 * @Method 설명 : cancelflag 업데이트 하기
	 * @param reservationInfoId
	 */
	@Override
	public void updateReservationByReservationInfoId(int reservationInfoId) {
		reservationMapper.updateCancelflagByReservationInfoId(reservationInfoId);
	}

	/**
	 * @Method Name : getReservationInfoCount
	 * @작성일 : 2019. 8. 19.
	 * @작성자 : 정승범
	 * @Method 설명 : 유효한 이메일이 있는지 카운트
	 * @param email
	 * @return
	 */
	public int getReservationInfoCount(String email) {
		return reservationMapper.countReservationInfo(email);
	}
}
