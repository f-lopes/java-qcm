package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.Teacher;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationService extends BaseService<Evaluation, String> {

	int takeEvaluation(String evaluationId, String qcmId, Student student, Set<String> answersIds, Date takenDate);

	List<EvaluationStudent> getTakenEvaluationsForStudent(Student student);

	List<Evaluation> getAvailableEvaluationsByGrade(Grade grade);

	boolean hasStudentTakenEvaluation(String studentId, String evaluationId);

	EvaluationStudent getTakenEvaluation(String evaluationId, String studentId);

	List<Evaluation> getEvaluationsByTeacher(Teacher teacher);

	List<Evaluation> getEvaluationsByTeacherForGrade(Teacher teacher, String gradeName);

	List<Evaluation> getEvaluationsByGrade(Grade grade);

	float getAverageMarkForEvaluation(Evaluation evaluation);

	Evaluation getFirstEvaluationByTeacher(Teacher teacher);

	List<Evaluation> getFinishedEvaluationsByTeacher(Teacher teacher);

	List<EvaluationStudent> getStudentsResultsByEvaluation(Evaluation evaluation);

    boolean hasStudentsTakenEvaluation(String evaluationId);

	List<Evaluation> getEvaluationsByCourseId(String courseId);
}
