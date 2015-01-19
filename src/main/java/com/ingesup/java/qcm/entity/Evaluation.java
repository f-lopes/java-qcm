package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "evaluation")
public class Evaluation extends BaseEntity {

	private Date startDate;

	private Date endDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "evaluation_id")
	private List<EvaluationStudent> students;

	public Evaluation() {
	}

	public Evaluation(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
