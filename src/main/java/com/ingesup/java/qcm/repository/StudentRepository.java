package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Student;

/**
 * Created by lopes_f on 21/01/2015.
 */
public interface StudentRepository extends UserRepository {

    @Override
    Student findByEmail(String email);
}
