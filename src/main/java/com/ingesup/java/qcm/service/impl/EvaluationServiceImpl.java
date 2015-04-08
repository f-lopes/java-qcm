package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.repository.*;
import com.ingesup.java.qcm.service.EvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements EvaluationService {

	private static final Logger logger = LoggerFactory.getLogger(EvaluationServiceImpl.class);
	private static final boolean debug = logger.isDebugEnabled();

	private final EvaluationRepository evaluationRepository;
	private final EvaluationStudentRepository evaluationStudentRepository;
	private final AnswerRepository answerRepository;
	private final CourseRepository courseRepository;
	private final GradeRepository gradeRepository;

	@Autowired
	public EvaluationServiceImpl(EvaluationRepository evaluationRepository, EvaluationStudentRepository evaluationStudentRepository,
								 AnswerRepository answerRepository, CourseRepository courseRepository, GradeRepository gradeRepository) {
		this.evaluationRepository = evaluationRepository;
		this.evaluationStudentRepository = evaluationStudentRepository;
		this.answerRepository = answerRepository;
		this.courseRepository = courseRepository;
		this.gradeRepository = gradeRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return evaluationRepository;
	}

	@Override
	public int takeEvaluation(String evaluationId, String qcmId, Student student, Set<String> answersIds, Date takenDate) {
		if (debug) {
			logger.debug("Taking evaluation {} for student {} and qcm {}", evaluationId, student.getEmail(), qcmId);
		}

		Evaluation evaluation = evaluationRepository.findOne(evaluationId);
		int evaluationMark = 0;

		for (String answerId : answersIds) {
			evaluationMark += answerRepository.findOne(answerId).getAnswerRate();
		}

		evaluationMark = ((evaluationMark * 20) / evaluation.getQcm().getQuestions().size());
		evaluationMark = evaluationMark > 20 ? 20 : evaluationMark;

		if (debug) {
			logger.debug("Student obtained {}", evaluationMark);
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
	public List<Evaluation> getEvaluationsByTeacherForGrade(Teacher teacher, String gradeName) {
		return evaluationRepository.findByTeacherAndGrade(teacher, gradeRepository.findFirstByName(gradeName));
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

    @Override
    public boolean hasStudentsTakenEvaluation(String evaluationId) {
        Evaluation evaluation = evaluationRepository.findOne(evaluationId);

        if (evaluation != null) {
            List<EvaluationStudent> evaluationResults = evaluationStudentRepository.findByEvaluation(evaluation);
            if (evaluationResults != null && evaluationResults.size() > 0) {
                return true;
            }
        }

        return false;
    }

	@Override
	public List<Evaluation> getEvaluationsByCourseId(String courseId) {
		Course course = courseRepository.findOne(courseId);
		List<Evaluation> evaluationsByCourse = new ArrayList<>();

		if (course != null) {
			evaluationsByCourse.addAll(evaluationRepository.findByCourse(course));
		}

		return evaluationsByCourse;
	}
}
