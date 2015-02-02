package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Student;

import java.util.List;

/**
 * Created by lopes_f on 21/01/2015.
 */
public interface StudentRepository extends BaseRepository<Student, String> {

    public Student findByEmail(String email);

//	@Query("SELECT S FROM Student S WHERE S.id = ")
//	public List<Student> findByEvaluation(Evaluation evaluation);
}
