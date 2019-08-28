/**
 * 
 */
package com.nts.reservation.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @FileName : LoggerInterceptor.java
 * @Project : 2019_2nd_intern_pjt03
 * @Date : 2019. 8. 23.
 * @Author : 정승범
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info(" START {}", handler.toString());
		logger.info(" IP address : {}  reqeust URI : {}  TIME : {}", request.getRemoteAddr(), request.getRequestURI(), new Date());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info(" END {}", handler.toString());
	}

}