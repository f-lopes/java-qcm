package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Vincent del Valle on 08/01/2015.
 */
@Entity
@Table(name = "qcm")
public class Qcm extends BaseEntity {

    private String name;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "qcm_id")
    private Set<Question> questions;

    public Qcm() {

    }

    public Qcm(String qcmName) {
        this.name = qcmName;
    }

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
