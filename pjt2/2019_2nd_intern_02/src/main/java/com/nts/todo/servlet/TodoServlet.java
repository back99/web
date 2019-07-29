package com.nts.todo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nts.todo.dao.TodoDao;
import com.nts.todo.dto.TodoDto;

/**
/* 메인 화면을 보여주는 클래스
*/
@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	/* todo doing done을 json으로 나눈후 request 하는 함수
	*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		try {
			List<TodoDto> todos = TodoDao.getInstance().getTodos();
			Gson gson = new Gson();

			request.setAttribute("todosJson", gson.toJson(todos));

			RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("/jsp/main.jsp");
			requestDispatcher.forward(request, response);

		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			response.sendError(400, "잘못된 요청입니다!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendError(500, "server error");
		}
	}

}
