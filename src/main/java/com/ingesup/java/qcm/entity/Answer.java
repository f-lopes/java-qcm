package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Vincent del Valle on 15/01/2015.
 */
@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {

    private String content;

    @ManyToOne
    private Question question;

    private int answerRate;

	public Answer(String content, Question question, int answerRate) {
		this.content = content;
		this.question = question;
		this.answerRate = answerRate;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getAnswerRate() {
        return answerRate;
    }

    public void setAnswerRate(int answerRate) {
        this.answerRate = answerRate;
    }
}
