package com.ingesup.java.qcm.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Simon on 15/01/2015.
 */
public class ValidateQcmForm {

    @NotEmpty
    private String qcmId;

    @NotEmpty
    private String[] selectedAnswers;

    public String getQcmId() {
        return qcmId;
    }

    public void setQcmId(String qcmId) {
        this.qcmId = qcmId;
    }

    public String[] getSelectedAnswers() {
        return selectedAnswers;
    }

    public void setSelectedAnswers(String[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }
}
