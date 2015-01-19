package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Answer;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Student;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationService extends BaseService<Evaluation, String> {

	public int takeEvaluation(Evaluation evaluation, Student student, Set<Answer> answers, Date takenDate);

	public List<EvaluationStudent> getTakenEvaluationsForStudent(String studentId);

	public List<Evaluation> getAvailableEvaluationsByGrade(String gradeId);
}
