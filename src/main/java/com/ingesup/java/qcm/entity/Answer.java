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

    private boolean correct;

    @ManyToOne
    private Question question;

    private int answerRate;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
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
