package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Role;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public interface RoleRepository extends BaseRepository<Role, String> {

	public Role findByName(String name);
}
