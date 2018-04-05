package by.vsu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.entity.Teacher;

public class TeacherDAO implements AbstractDAO<Teacher, Long> {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASSWORD = "qweasd";
	private static final String SELECT_ALL = "SELECT * FROM teacher";
	private static final String SELECT_BY_ID = "SELECT * FROM teacher WHERE id = ?";
	private static final String INSERT = "INSERT INTO teacher(name, department) VALUES(?, ?)";
	private static final String UPDATE = "UPDATE teacher SET name = ?, department = ? WHERE id = ?";
	private static final String DELETE_BY_ID = "DELETE FROM teacher WHERE id = ?";
	private static final String DELETE_ALL = "DELETE FROM teacher";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Teacher> getAll() {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Teacher> teachers = new ArrayList<Teacher>();

			while (resultSet.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getLong(1));
				teacher.setName(resultSet.getString(2));
				teacher.setDepartment(resultSet.getString(3));
				teachers.add(teacher);
			}
			return teachers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Teacher getById(Long id) {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Teacher teacher = new Teacher();
			if (resultSet.next()) {
				teacher.setId(resultSet.getLong(1));
				teacher.setName(resultSet.getString(2));
				teacher.setDepartment(resultSet.getString(3));
			}
			return teacher;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Teacher entity) {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getDepartment());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Teacher entity) {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getDepartment());
			preparedStatement.setLong(3, entity.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
