package by.vsu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.entity.Department;
import by.vsu.entity.Faculty;

public class DepartmentDAO implements AbstractDAO<Department, Long>{

	private static final String SELECT_ALL = "SELECT * FROM department";
	private static final String SELECT_BY_ID = "SELECT * FROM department WHERE id = ?";
	private static final String SELECT_BY_FACULTY = "SELECT * FROM department WHERE faculty = ?";
	private static final String INSERT = "INSERT INTO department(name, faculty) VALUES(?, ?)";
	private static final String UPDATE = "UPDATE department SET name = ?, faculty = ? WHERE id = ?";
	private static final String DELETE_BY_ID = "DELETE FROM department WHERE id = ?";
	
	
	@Override
	public List<Department> getAll() {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Department> departments = new ArrayList<Department>();

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getLong(1));
				department.setName(resultSet.getString(2));
				department.setFaculty(resultSet.getString(3));
				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Department getById(Long id) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			Department department = new Department();
			
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				department.setId(resultSet.getLong(1));
				department.setName(resultSet.getString(2));
				department.setFaculty(resultSet.getString(3));
			}
			
			return department;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Department> getByFaculty(Faculty faculty) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_FACULTY);
			preparedStatement.setString(1, faculty.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Department> departments = new ArrayList<Department>();

			while (resultSet.next()) {
				Department department = new Department();
				department.setId(resultSet.getLong(1));
				department.setName(resultSet.getString(2));
				department.setFaculty(resultSet.getString(3));
				departments.add(department);
			}
			return departments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Department entity) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
			
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getFaculty());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Department entity) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
			
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getFaculty());
			preparedStatement.setLong(3, entity.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Department entity) {
		TeacherDAO tDAO = new TeacherDAO();
		
		tDAO.delete(entity);
		
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			
			preparedStatement.setLong(1, entity.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		this.getAll().forEach(this::delete);
	}

	public void delete(Faculty faculty) {
		getByFaculty(faculty).forEach(this::delete);
	}

}
