package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Qcm;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public class QcmForm {

	private String qcmName;

	public Qcm getQcm() {
		return new Qcm(qcmName);
	}


	public String getQcmName() {
		return qcmName;
	}

	public void setQcmName(String qcmName) {
		this.qcmName = qcmName;
	}
}
