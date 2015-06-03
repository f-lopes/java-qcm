package com.ingesup.java.qcm.message;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public enum MessageType {

    INFO, SUCCESS, WARNING, DANGER;

    private final String cssClass;

    MessageType(){
        cssClass = name().toLowerCase();
    }

    public String getCssClass(){
        return cssClass;
    }
}
