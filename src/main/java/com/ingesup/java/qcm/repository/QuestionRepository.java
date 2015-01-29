package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Question;

import java.util.List;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public interface QuestionRepository extends BaseRepository<Question, String> {

	public List<Question> findByQcm(Qcm qcm);
}
