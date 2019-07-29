package com.nts.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
/* 비동기 ajax 통신을 위한 클래스
*/

@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	/* post방식으로 id 와 type을 받고 통신을 하기 위한 메소드
	*/
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		try {
			long requestId = Long.parseLong(request.getParameter("id"));
			String requestType = request.getParameter("type");

			TodoDto todoDto = new TodoDto(requestId, requestType);
			TodoDao todoModifyType = TodoDao.getInstance();
			todoModifyType.updateTodo(todoDto);

			response.setContentType("text/html;charset=UTF-8");

			PrintWriter printResponse = response.getWriter();
			printResponse.print("success");
			printResponse.flush();

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			response.sendError(400, "잘못된 요청입니다!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendError(500, "server error");
		}

	}

}
