package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Simon on 08/01/2015.
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {

	private String label;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Set<Answer> answers;

    @ManyToOne
    private Qcm qcm;

	public Question(Qcm qcm, String label) {
		this.qcm = qcm;
		this.label = label;
	}

    public Question() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Qcm getQcm() {
        return qcm;
    }

    public void setQcm(Qcm qcm) {
        this.qcm = qcm;
    }
}
