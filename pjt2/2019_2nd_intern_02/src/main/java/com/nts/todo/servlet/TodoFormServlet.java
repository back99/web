package com.nts.todo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 새로 todoList를 등록하는 클래스
 */
@WebServlet("/form")
public class TodoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 할일등록 화면으로 포워딩
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request
			.getRequestDispatcher("/jsp/from.jsp");
		requestDispatcher.forward(request, response);

	}

}
