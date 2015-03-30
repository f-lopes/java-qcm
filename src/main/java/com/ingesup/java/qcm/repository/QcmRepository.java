package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Teacher;

import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface QcmRepository extends BaseRepository<Qcm, String> {

    List<Qcm> findByTeacher(Teacher teacher);
}
