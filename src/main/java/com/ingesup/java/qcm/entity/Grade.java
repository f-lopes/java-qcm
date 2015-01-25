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

    public Grade() {

    }

    public Grade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grade)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Grade grade = (Grade) o;

        if (name != null ? !name.equals(grade.name) : grade.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
