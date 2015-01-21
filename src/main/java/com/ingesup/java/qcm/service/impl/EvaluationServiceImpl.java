package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.EvaluationRepository;
import com.ingesup.java.qcm.repository.EvaluationStudentRepository;
import com.ingesup.java.qcm.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements EvaluationService {

	private EvaluationRepository evaluationRepository;

	private EvaluationStudentRepository evaluationStudentRepository;

	@Autowired
	public EvaluationServiceImpl(EvaluationRepository evaluationRepository,
								 EvaluationStudentRepository evaluationStudentRepository) {
		this.evaluationRepository = evaluationRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return evaluationRepository;
	}

	@Override
	public int takeEvaluation(Evaluation evaluation, Student student, Set<Answer> answers, Date takenDate) {
		int evaluationMark = 0;

		for (Answer answer : answers) {
			evaluationMark += answer.isCorrect() ? 1 : 0;
		}

		EvaluationStudent evaluationStudent = new EvaluationStudent(evaluation, student, evaluationMark, takenDate);

		evaluationStudentRepository.save(evaluationStudent);

		return evaluationMark;
	}

	@Override
	public List<EvaluationStudent> getTakenEvaluationsForStudent(Student student) {
		return evaluationStudentRepository.findByStudent(student);
	}

	@Override
	public List<Evaluation> getAvailableEvaluationsByGrade(Grade grade) {
		return evaluationRepository.findAvailableByGrade(grade);
	}
}
