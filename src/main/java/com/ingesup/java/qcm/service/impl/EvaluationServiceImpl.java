package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.repository.AnswerRepository;
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

	private AnswerRepository answerRepository;

	@Autowired
	public EvaluationServiceImpl(EvaluationRepository evaluationRepository, EvaluationStudentRepository evaluationStudentRepository,
								 AnswerRepository answerRepository) {
		this.evaluationRepository = evaluationRepository;
		this.evaluationStudentRepository = evaluationStudentRepository;
		this.answerRepository = answerRepository;
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

		// TODO calculate evaluationMark / 20
		evaluationMark = evaluationMark / evaluation.getQcm().getQuestions().size() * 20;
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

	@Override
	public boolean hasStudentTakenEvaluation(String studentId, String evaluationId) {
		return evaluationStudentRepository.findOne(new EvaluationStudentPk(evaluationId, studentId)) != null;
	}

	@Override
	public EvaluationStudent getTakenEvaluation(String evaluationId, String studentId) {
		return evaluationStudentRepository.findOne(new EvaluationStudentPk(evaluationId, studentId));
	}

	@Override
	public List<Evaluation> getEvaluationsByTeacher(Teacher teacher) {
		return evaluationRepository.findByTeacher(teacher);
	}

	@Override
	public List<Evaluation> getEvaluationsByGrade(Grade grade) {
		return evaluationRepository.findByGrade(grade);
	}

	@Override
	public float getAverageMarkForEvaluation(Evaluation evaluation) {
		List<EvaluationStudent> evaluationResults = evaluationStudentRepository.findByEvaluation(evaluation);
		float averageMarks = 0;
		if (evaluationResults != null) {
			int nbStudents = evaluationResults.size();

			for (EvaluationStudent evaluationResult : evaluationResults) {
				averageMarks += evaluationResult.getMark();
			}

			averageMarks = averageMarks / nbStudents;
		}

		return averageMarks;
	}

	@Override
	public Evaluation getFirstEvaluationByTeacher(Teacher teacher) {
		return evaluationRepository.findFirstByTeacher(teacher);
	}

	@Override
	public List<Evaluation> getFinishedEvaluationsByTeacher(Teacher teacher) {
		return evaluationRepository.findFinishedEvaluationsByTeacher(teacher);
	}

	@Override
	public List<EvaluationStudent> getStudentsResultsByEvaluation(Evaluation evaluation) {
		return evaluationStudentRepository.findByEvaluation(evaluation);
	}
}
