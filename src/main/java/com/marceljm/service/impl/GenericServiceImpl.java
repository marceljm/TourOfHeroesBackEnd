package com.marceljm.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marceljm.persistence.GenericDAO;
import com.marceljm.service.GenericService;

@Service
public class GenericServiceImpl<T> implements GenericService<T> {

	@Inject
	GenericDAO<T> dao;

	@Override
	public List<T> select(Class<T> clazz) {
		return dao.select(clazz);
	}

	@Override
	public T select(Class<T> clazz, Long id) {
		return dao.select(clazz, id);
	}

	@Override
	public T select(Class<T> clazz, String name) {
		return dao.select(clazz, name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(T t) {
		dao.insert(t);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(T t) {
		dao.update(t);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(T t) {
		dao.delete(t);
	}

}
