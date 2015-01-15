package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Vincent del Valle on 08/01/2015.
 */
@Entity
@Table(name = "qcm")
public class QCM extends BaseEntity {

    private String name;

    @ManyToOne()
    private Set<Question> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
