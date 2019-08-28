/**
 * 
 */
package com.nts.reservation.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Display.DisplayInfoDto;
import com.nts.reservation.dto.Display.DisplayInfoImageDto;

/**
 * @FileName : DisplayMapper.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 9.
 * @Author : 정승범
 */
@Repository
public interface DisplayMapper {

	public float selectAvgScoreByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

	public DisplayInfoDto selectDisplayInfoByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

	public DisplayInfoImageDto selectDisplayInfoImagesByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

	public String selectEtIamgeFileName(@Param("displayInfoId") int displayInfoId);

	public int selectTotalCountByDisplayInfoId(@Param("displayInfoId") int displayInfoId);

}
