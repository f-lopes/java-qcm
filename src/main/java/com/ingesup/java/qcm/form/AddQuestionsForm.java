package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Question;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class AddQuestionsForm {



	private String label;

	private int points;

	/**
	 * Generate a Question object from label and points
	 */
	public Question createQuestion(){
		Question q = new Question();
		q.setLabel(label);
		q.setPoints(points);
		return q;
	}


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
