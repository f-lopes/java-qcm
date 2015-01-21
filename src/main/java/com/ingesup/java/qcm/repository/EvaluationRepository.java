package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Grade;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationRepository extends BaseRepository<Evaluation, String> {

	@Query("SELECT E FROM Evaluation E WHERE (NOW() BETWEEN E.startDate AND E.endDate) AND E.grade = ?1")
	public List<Evaluation> findAvailableByGrade(Grade grade);
}
