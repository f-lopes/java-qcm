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
	public void add(T entity) {
		getRepository().save(entity);
	}

	@Override
	public T get(I primaryKey) {
		return (T) getRepository().findOne(primaryKey);
	}
}
