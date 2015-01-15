package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Qcm;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public class QcmForm {

	private String qcmName;

	@NotEmpty
	private String qcmTest;

	public Qcm getQcm() {
		return new Qcm(qcmName);
	}

	public String getQcmName() {
		return qcmName;
	}

	public void setQcmName(String qcmName) {
		this.qcmName = qcmName;
	}

	public String getQcmTest() {
		return qcmTest;
	}

	public void setQcmTest(String qcmTest) {
		this.qcmTest = qcmTest;
	}
}
