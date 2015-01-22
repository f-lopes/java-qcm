package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.repository.*;
import com.ingesup.java.qcm.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements EvaluationService {

	private EvaluationRepository evaluationRepository;

	private EvaluationStudentRepository evaluationStudentRepository;

	private QcmRepository qcmRepository;

	private QuestionRepository questionRepository;

	private AnswerRepository answerRepository;

	@Autowired
	public EvaluationServiceImpl(EvaluationRepository evaluationRepository,
								 EvaluationStudentRepository evaluationStudentRepository,
								 QcmRepository qcmRepository, QuestionRepository questionRepository,
								 AnswerRepository answerRepository) {
		this.evaluationRepository = evaluationRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return evaluationRepository;
	}

	@Override
	public int takeEvaluation(String evaluationId, String qcmId, Student student, Set<String> answersIds, Date takenDate) {
		Evaluation evaluation = evaluationRepository.findOne(evaluationId);
		int evaluationMark = 0;

		for (String answerId : answersIds) {
			evaluationMark += answerRepository.findOne(answerId).getAnswerRate();
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
