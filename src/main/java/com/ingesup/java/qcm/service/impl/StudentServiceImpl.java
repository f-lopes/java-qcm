package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.StudentRepository;
import com.ingesup.java.qcm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 21/01/2015.
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, String> implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public BaseRepository getRepository() {
        return studentRepository;
    }
}
