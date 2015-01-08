package com.ingesup.java.qcm.service;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface BaseService <T, I> {

	public void add(T entity);

	public T get(I primaryKey);
}
