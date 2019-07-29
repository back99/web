package com.nts;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* 요청받은 현재 시간을 보여주는 클래스 
*/
@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String dateTimeForm = "yyyy/MM/dd HH:mm";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String dateTimeString = getCurrentDateTime();

		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>");
		out.println("	<header><a href=\"/2019_2nd_intern/index.html\">메인화면</a></header>");
		out.println("		<div style=white-space:nowrap;position:absolute;top:50%;left:30%;font-size:50;>현재시간 : "
			+ dateTimeString);
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");

	}

	/*
	* 지금 현재 시간을 알려주는 메소드
	*/
	public String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return currentDateTime.format(DateTimeFormatter.ofPattern(dateTimeForm));

	}

}
