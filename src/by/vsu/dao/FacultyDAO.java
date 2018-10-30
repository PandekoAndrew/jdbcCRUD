package by.vsu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.entity.Faculty;

public class FacultyDAO implements AbstractDAO<Faculty, Long>{

	private static final String SELECT_ALL = "SELECT * FROM faculty";
	private static final String SELECT_BY_ID = "SELECT * FROM faculty WHERE id = ?";
	private static final String INSERT = "INSERT INTO faculty(name) VALUES(?)";
	private static final String UPDATE = "UPDATE faculty SET name = ?, WHERE id = ?";
	private static final String DELETE_BY_ID = "DELETE FROM faculty WHERE id = ?";
	
	@Override
	public List<Faculty> getAll() {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Faculty> faculties = new ArrayList<Faculty>();

			while (resultSet.next()) {
				Faculty faculty = new Faculty();
				faculty.setId(resultSet.getLong(1));
				faculty.setName(resultSet.getString(2));
				faculties.add(faculty);
			}
			
			return faculties;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Faculty getById(Long id) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			Faculty faculty = new Faculty();
			
			if (resultSet.next()) {
				faculty.setId(resultSet.getLong(1));
				faculty.setName(resultSet.getString(2));
			}
			
			return faculty;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Faculty entity) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
			
			preparedStatement.setString(1, entity.getName());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Faculty entity) {
		try (Connection connection = Connector.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
			
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setLong(2, entity.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Faculty entity) {
		DepartmentDAO dDAO = new DepartmentDAO();
		
		dDAO.delete(entity);
		
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

}
