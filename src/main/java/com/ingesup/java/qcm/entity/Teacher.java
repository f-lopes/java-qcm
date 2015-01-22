package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity(name = "teacher")
@Table(name = "teacher")
@PrimaryKeyJoinColumn (name = "teacher_id", referencedColumnName = "id")
public class Teacher extends User {

    private String speciality;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
