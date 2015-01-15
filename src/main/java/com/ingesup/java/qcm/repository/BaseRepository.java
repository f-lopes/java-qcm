package com.ingesup.java.qcm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable> extends CrudRepository<T, I> {

}
