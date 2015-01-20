package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Grade;

import java.util.Date;
import java.util.List;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public interface EvaluationRepository extends BaseRepository<Evaluation, String> {

	public List<Evaluation> findByGradeAndStartDateBefore(Grade grade, Date beforeDate);
}
