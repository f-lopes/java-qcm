package com.ingesup.java.qcm.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Simon on 15/01/2015.
 */
public class ValidateQcmForm {

    @NotEmpty
    private String qcmId;

    @NotEmpty
    private String evalId;

    @NotEmpty
    private String[] selectedAnswers;

    public ValidateQcmForm(String evalId, String qcmId) {
        this.evalId = evalId;
        this.qcmId = qcmId;
    }

    public ValidateQcmForm() {
    }

    public String getQcmId() {
        return qcmId;
    }

    public void setQcmId(String qcmId) {
        this.qcmId = qcmId;
    }

    public String getEvalId() {
        return evalId;
    }

    public void setEvalId(String evalId) {
        this.evalId = evalId;
    }

    public String[] getSelectedAnswers() {
        return selectedAnswers;
    }

    public void setSelectedAnswers(String[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }
}
