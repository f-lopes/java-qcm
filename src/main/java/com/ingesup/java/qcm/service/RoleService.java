package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Role;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
public interface RoleService extends BaseService<Role, String> {

	@Cacheable("rolesCache")
	@Override
	List<Role> getAll();

	@Cacheable("roleCache")
	@Override
	Role get(String primaryKey);
}
