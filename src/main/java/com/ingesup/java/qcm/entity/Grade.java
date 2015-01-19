package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/19/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "grade")
public class Grade extends BaseEntity {

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private Set<Student> students;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private List<Evaluation> evaluations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
