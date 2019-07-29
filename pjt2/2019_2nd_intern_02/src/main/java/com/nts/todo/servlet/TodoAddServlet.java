package com.nts.todo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
 * 새로 todoList를 추가하고 리다이렉트로 돌아가는 클래스
 */
@WebServlet("/add")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TODO_TITLE_MAX_LIMIT = 24;

	/**
	 * name title sequence 값을 받아와서 db에 데이터를 추가하는 메소드
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		if (!requestParameterValidate(request.getParameter("name"), request.getParameter("title"),
			request.getParameter("sequence"))) {

			String erroMessage = "입력값이 잘못되었습니다.";
			request.setAttribute("errorMessage", erroMessage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/from.jsp");
			dispatcher.forward(request, response);
		} else {
			try {

				String requestName = request.getParameter("name");
				String requestTitle = request.getParameter("title");
				int requestSequence = Integer.parseInt(request.getParameter("sequence"));

				TodoDto todoDto = new TodoDto(requestName, requestTitle, requestSequence);

				TodoDao todoAddDao = TodoDao.getInstance();
				todoAddDao.addTodo(todoDto);

				response.sendRedirect("/webapiexam/todo");
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
				response.sendError(400, "잘못된 요청입니다!");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.sendError(500, "server error");
			}

		}
	}

	/**
	 * 리퀘스트 파라미터 유효성 검사 메소드
	 */
	private boolean requestParameterValidate(String requestName, String requestTitle, String requestSequence) {
		if (requestName == null || requestTitle == null || requestSequence == null) {
			return false;
		} else if (requestName.equals("") || requestTitle.equals("")) {
			return false;
		} else if (requestTitle.length() > TODO_TITLE_MAX_LIMIT) {
			return false;
		}
		return true;
	}

}
