/**
 * 
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Reservation.ReservationInfo;
import com.nts.reservation.dto.Reservation.ReservationParam;
import com.nts.reservation.dto.Reservation.ReservationPrice;
import com.nts.reservation.dto.Reservation.ReserveResponse;

/**
 * @FileName : ReservationMapper.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
@Repository
public interface ReservationMapper {

	public ReserveResponse selectReserveResponseByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

	public void insertReservationInfo(@Param("reservationParam") ReservationParam reservationParam);

	public void insertPricesInfo(@Param("prices") List<ReservationPrice> prices);

	public List<ReservationInfo> selectReserveInfoByEmail(@Param("email") String email);

	public void updateCancelflagByReservationInfoId(@Param("reservationInfoId") int reservationInfoId);

	public int countReservationInfo(@Param("email") String email);

}
