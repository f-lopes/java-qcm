package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.repository.BaseRepository;

import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface BaseService <T, I> {

	List<T> getAll();

	T add(T entity);

	Iterable<T> add(Iterable<T> entities);

	T get(I primaryKey);

	void removeEntity(T entity);

	void remove(I primaryKey);

	T update(T entity);

	BaseRepository getRepository();
}
