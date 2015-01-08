package com.ingesup.java.qcm.entity;

/**
 * Created by Simon on 08/01/2015.
 */
public class Question extends BaseEntity {
    private int points;
    private String label;

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
}
