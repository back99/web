package com.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nts.todo.dto.TodoDto;

public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user13";
	private static final String DB_USER = "user13";
	private static final String DB_PASSWD = "user13";
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String SQL_GET_TODOS = "select id, title, name, sequence, type, regdate from todo order by regdate desc";
	private static final String SQL_UPDATE_TYPE = "update todo set type=? where id=?";
	private static final String SQL_ADD = "insert into todo(title,name,sequence) values(?,?,?)";
	private static final String AFTER_DATE_FORM = "yyyy.MM.dd";

	/**
	 * 싱글톤 객체 생성을 위한 Holder 클래스
	 */
	private static class TodoDaoHolder {
		private static final TodoDao INSTANCE = new TodoDao();
	}

	/**
	 * 싱글톤을 위한 인스턴스 반환 메소드
	 */
	public static TodoDao getInstance() {
		return TodoDaoHolder.INSTANCE;
	}

	/**
	 * mysqlDriver 사용을 위한 생성자
	 */
	private TodoDao() {
		try {
			Class.forName(MYSQL_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("mysqlDriver 오류!");
		}
	}

	/**
	/* db의 값을 가져오기 위한 select 쿼리문 메소드
	*/
	public List<TodoDto> getTodos() throws Exception {
		List<TodoDto> todoList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TODOS);
			ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				SimpleDateFormat afterDateForm = new SimpleDateFormat(AFTER_DATE_FORM);
				long id = resultSet.getLong(1);
				String title = resultSet.getString(2);
				String name = resultSet.getString(3);
				int sequence = resultSet.getInt(4);
				String type = resultSet.getString(5);
				String regdate = afterDateForm.format(resultSet.getDate(6));

				TodoDto todoGet = new TodoDto(id, title, name, sequence, type, regdate);

				todoList.add(todoGet);

			}
		} catch (Exception e) {
			throw e;
		}

		return todoList;
	}

	/**
	/* type 을 바꾸어주기 위한 update 쿼리문 메소드
	*/
	public void updateTodo(TodoDto todoDto) throws Exception {
		long dbId = todoDto.getId();
		String dbType = null;

		if (todoDto.getType().equals("TODO")) {
			dbType = "DOING";
		} else if (todoDto.getType().equals("DOING")) {
			dbType = "DONE";
		}

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TYPE);) {

			preparedStatement.setString(1, dbType);
			preparedStatement.setLong(2, dbId);

			preparedStatement.executeUpdate();

		} catch (Exception e) {

			throw e;
		}
	}

	/**
	/* db에 todo를 추가하는 insert 쿼리문 메소드
	*/
	public void addTodo(TodoDto todoDto) throws Exception {

		int dbSequence = todoDto.getSequence();
		String dbName = todoDto.getName();
		String dbTitle = todoDto.getTitle();

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD);) {

			preparedStatement.setString(1, dbTitle);
			preparedStatement.setString(2, dbName);
			preparedStatement.setInt(3, dbSequence);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

}
