/**
 * 
 */
package com.nts.reservation.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName : Fileinfo.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 22.
 * @Author : 정승범
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

	private String fileName;
	private String saveFileName;
	private String contentType;
	private int id;

}
