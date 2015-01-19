package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationService extends BaseService<Evaluation, String> {

	public int takeEvaluation(Evaluation evaluation, Student student, Set<Answer> answers, Date takenDate);

	public List<EvaluationStudent> getTakenEvaluationsForStudent(Student student);

	public List<Evaluation> getAvailableEvaluationsByGrade(Grade grade);
}
