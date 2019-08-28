/**
 * 
 */
package com.nts.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.Comment.CommentParam;
import com.nts.reservation.dto.Comment.FileInfo;
import com.nts.reservation.service.CommentpageService;

/**
 * @FileName : ReviewWritepaeRestController.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 21.
 * @Author : 정승범
 */
@RestController
public class ReviewWritepaeRestController {

	private final CommentpageService commentpageService;

	@Value("${storage.path}")
	private String STORAGE_PATH;
	private static final String JPG_TYPE = "image/jpg";
	private static final String PNG_TYPE = "image/png";

	public ReviewWritepaeRestController(CommentpageService commentpageService) {
		this.commentpageService = commentpageService;
	}

	/**
	 * @Method Name : requestComment
	 * @작성일 : 2019. 8. 22.
	 * @작성자 : 정승범
	 * @Method 설명 : 코멘트 저장하기
	 * @param commentParam
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("/api/reviewWrite")
	public void requestComment(@ModelAttribute CommentParam commentParam) throws IOException {
		String imageContentType = commentParam.getImageFile().getContentType();

		if (!imageContentType.equals(JPG_TYPE) && !imageContentType.equals(PNG_TYPE)) {
			throw new FileNotFoundException("이미지 형식이 맞지 않습니다.");
		}

		if (commentParam.getImageFile().isEmpty()) {
			throw new FileNotFoundException("이미지가 들어오지 않았습니다.");
		}

		String imageFileName = UUID.randomUUID().toString() + commentParam.getImageFile().getOriginalFilename();
		String filePath = Paths.get(STORAGE_PATH, imageFileName).toString();

		File ImageFile = new File(filePath);
		commentParam.getImageFile().transferTo(ImageFile);

		FileInfo fileInfo = new FileInfo();
		fileInfo.setContentType(imageContentType);
		fileInfo.setFileName(imageFileName);
		fileInfo.setSaveFileName(filePath);

		commentpageService.addCommentImage(commentParam, fileInfo);
	}

	/**
	 * @Method Name : downloadCommentImageFile
	 * @작성일 : 2019. 8. 27.
	 * @작성자 : 정승범
	 * @Method 설명 : 이미지 받아오기
	 * @param fileId
	 * @return
	 * @throws IOException
	 */
	@GetMapping(path = "/api/image/{fileId}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public byte[] downloadCommentImageFile(@PathVariable("fileId") int fileId) throws IOException {
		String imageFileSavePath = commentpageService.getCommentImageFileSavePath(fileId);
		FileInputStream fileInputStream = new FileInputStream(imageFileSavePath);

		return IOUtils.toByteArray(fileInputStream);
	}

}
