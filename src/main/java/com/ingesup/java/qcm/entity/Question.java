package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Simon on 08/01/2015.
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {
    private int points;
    private String label;

    @ManyToOne()
    private Set<Answer> answers;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
