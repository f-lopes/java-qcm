package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Course;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Teacher;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationRepository extends BaseRepository<Evaluation, String> {

	@Query("SELECT E FROM Evaluation E WHERE (NOW() BETWEEN E.startDate AND E.endDate) AND E.grade = ?1")
	public List<Evaluation> findAvailableByGrade(Grade grade);

	public List<Evaluation> findByTeacher(Teacher teacher);

	public List<Evaluation> findByGrade(Grade grade);

	public Evaluation findFirstByTeacher(Teacher teacher);

	@Query("SELECT E FROM Evaluation E WHERE (NOW() > E.endDate) AND E.teacher = ?1")
	public List<Evaluation> findFinishedEvaluationsByTeacher(Teacher teacher);

	public List<Evaluation> findByCourse(Course course);
}
