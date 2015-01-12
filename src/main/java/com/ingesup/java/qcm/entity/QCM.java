package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Vincent del Valle on 08/01/2015.
 */
@Entity
@Table(name = "qcm")
public class QCM extends BaseEntity {

    private String name;

}
