package com.marceljm.persistence;

import java.util.List;

public interface GenericDAO<T> {

	public List<T> select(Class<T> clazz);

	public T select(Class<T> clazz, Long id);

	public T select(Class<T> clazz, String name);

	public void insert(T t);

	public void update(T t);

	public void delete(T t);

	public boolean contains(T t);

}
