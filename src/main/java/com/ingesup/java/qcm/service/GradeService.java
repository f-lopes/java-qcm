package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Grade;

/**
 * Created by lopes_f on 1/19/2015.
 * <florian.lopes@outlook.com>
 */
public interface GradeService extends BaseService<Grade, String> {

	Grade getGradeByName(String name);
}
