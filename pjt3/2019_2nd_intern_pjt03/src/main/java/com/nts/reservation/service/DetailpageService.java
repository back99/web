/**
 * 
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.Display.DisplayInfoResponse;

/**
 * @FileName : DetailpageService.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 7. 31.
 * @Author : 정승범
 */
public interface DetailpageService {

	public DisplayInfoResponse getDisplayInfoResponseByDisplayInfoId(int displayInfoId, Boolean commentLimitFlag);

	public String getEtImageResponseByDisplayInfoId(int displayInfoId);

}
