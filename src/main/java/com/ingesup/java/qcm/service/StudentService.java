package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;

import java.util.List;

/**
 * Created by lopes_f on 21/01/2015.
 */
public interface StudentService extends BaseService<Student, String> {

	List<Student> getStudentsByGrade(Grade grade);
}
