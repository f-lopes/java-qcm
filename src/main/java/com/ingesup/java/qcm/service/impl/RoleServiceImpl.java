package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Role;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.RoleRepository;
import com.ingesup.java.qcm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lopes_f on 1/25/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public BaseRepository<Role, String> getRepository() {
		return roleRepository;
	}

	@Cacheable("roles")
	@Override
	public List<Role> getAll() {
		return super.getAll();
	}

	@Cacheable("roles")
	@Override
	public Role get(String primaryKey) {
		return super.get(primaryKey);
	}
}
