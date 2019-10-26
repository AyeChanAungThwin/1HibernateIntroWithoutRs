package acat.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

import acat.hibernate.utils.HibernateUtils;

public abstract class AbstractDao<T extends Serializable> implements IAbstractDao<T> {
	
	private Class<T> entityName;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.entityName =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T findOne(long id) {
		return (T) getCurrentSession().get(entityName, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getCurrentSession().createQuery("from "+entityName.getName()).getResultList();
	}
	
	public T save(T entity) {
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public T update(T entity) {
		return (T) getCurrentSession().merge(entity);
	}
	
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}
	
	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	
	protected Session getCurrentSession() {
		return HibernateUtils.getSessionFactory().openSession();
	}
}
