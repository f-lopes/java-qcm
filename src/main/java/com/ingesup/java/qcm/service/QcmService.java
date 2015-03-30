package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Teacher;

import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface QcmService extends BaseService<Qcm, String> {

    List<Qcm> getQcmByTeacher(Teacher teacher);
}
