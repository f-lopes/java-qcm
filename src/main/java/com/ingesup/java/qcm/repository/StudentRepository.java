package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;

import java.util.List;

/**
 * Created by lopes_f on 21/01/2015.
 */
public interface StudentRepository extends BaseRepository<Student, String> {

    public Student findByEmail(String email);

	public List<Student> findByGrade(Grade grade);
}
