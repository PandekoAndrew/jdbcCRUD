package by.vsu.dao;

import java.util.List;

public interface AbstractDAO<E, K> {
	
	public List<E> getAll();
	
	public E getById(K id);
	
	public void insert(E entity);
	
	public void update(E entity);
	
	public void delete(E entity);
	
	public void deleteAll();
}
