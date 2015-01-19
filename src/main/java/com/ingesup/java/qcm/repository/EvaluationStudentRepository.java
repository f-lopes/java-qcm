package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.EvaluationStudentPk;
import com.ingesup.java.qcm.entity.Student;

import java.util.List;

/**
 * Created by lopes_f on 1/17/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationStudentRepository extends BaseRepository<EvaluationStudent, EvaluationStudentPk> {

    public List<EvaluationStudent> findByStudent(Student student);
}
