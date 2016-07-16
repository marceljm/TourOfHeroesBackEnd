package com.marceljm.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.marceljm.persistence.GenericDAO;

@Repository
public class GenericDAOImpl<T> implements GenericDAO<T> {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<T> select(Class<T> clazz) {
		return manager.createQuery("SELECT t FROM " + clazz.getSimpleName() + " t").getResultList();
	}

	@Override
	public T select(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T select(Class<T> clazz, String name) {
		return (T) manager.createQuery("SELECT t FROM " + clazz.getSimpleName() + " t WHERE name = '" + name + "'")
				.getSingleResult();
	}

	@Override
	public void insert(T t) {
		manager.merge(t);
	}

	@Override
	public void update(T t) {
		manager.merge(t);
	}

	@Override
	public void delete(T t) {
		manager.remove(manager.contains(t) ? t : manager.merge(t));
	}

	@Override
	public boolean contains(T t) {
		return manager.contains(t);
	}
}
