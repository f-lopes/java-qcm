package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Grade;

/**
 * Created by lopes_f on 1/19/2015.
 * <florian.lopes@outlook.com>
 */
public interface GradeRepository extends BaseRepository<Grade, String> {

	public Grade findFirstByName(String name);
}
