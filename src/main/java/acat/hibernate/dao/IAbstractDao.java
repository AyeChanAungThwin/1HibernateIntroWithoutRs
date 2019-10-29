package acat.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface IAbstractDao<T extends Serializable> {
	
	T findOne(long id);
	
	List<T> findAll();
	
	T save(T entity);
	
	T update(T entity);
	
	void delete(T entity);
	
	void deleteById(long entityId);
}
