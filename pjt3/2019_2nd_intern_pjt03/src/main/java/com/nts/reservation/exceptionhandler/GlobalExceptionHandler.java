/**
 * 
 */
package com.nts.reservation.exceptionhandler;

import java.io.IOException;
import java.io.InvalidObjectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @FileName : GlobalExceptionHandler.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 19.
 * @Author : 정승범
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * @Method Name : restControllerHandleException
	 * @작성일 : 2019. 8. 26.
	 * @작성자 : 정승범
	 * @Method 설명 : restcontroller exception 처리
	 * @param exception
	 * @param req
	 * @return
	 */
	@ExceptionHandler({IOException.class, InvalidObjectException.class})
	public ResponseEntity<String> restControllerHandleException(Exception exception) {
		logger.info("ERROR MESSAGE : {}", exception.getMessage());

		return new ResponseEntity<String>("에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @Method Name : handleException
	 * @작성일 : 2019. 8. 26.
	 * @작성자 : 정승범
	 * @Method 설명 : exception 처리
	 * @param exception
	 * @param req
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	public ModelAndView handleException(Exception exception) {
		logger.info("ERROR MESSAGE : {}", exception.getMessage());

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("message", exception.getMessage());

		return modelAndView;
	}

}
