package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.QcmRepository;
import com.ingesup.java.qcm.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 1/12/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class QcmServiceImpl extends BaseServiceImpl<Qcm, String> implements QcmService {

	private QcmRepository qcmRepository;

	@Autowired
	public QcmServiceImpl(QcmRepository qcmRepository) {
		this.qcmRepository = qcmRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return qcmRepository;
	}
}
