package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public abstract class BaseServiceImpl<T, I extends Serializable> implements BaseService<T, I> {

	@Override
	public List<T> getAll() {
		return (List<T>) getRepository().findAll();
	}

	@Override
	public T add(T entity) {
		return (T) getRepository().save(entity);
	}

	@Override
	public Iterable<T> add(Iterable<T> entities) {
		return getRepository().save(entities);
	}

	@Override
	public T get(I primaryKey) {
		return (T) getRepository().findOne(primaryKey);
	}

	@Override
	public void removeEntity(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void remove(I primaryKey) {
		getRepository().delete(primaryKey);
	}

	@Override
	public T update(T entity) {
		return (T) getRepository().save(entity);
	}
}
