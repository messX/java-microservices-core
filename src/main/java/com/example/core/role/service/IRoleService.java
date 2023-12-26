package com.example.core.role.service;


import com.example.core.role.module.Role;
import com.example.core.role.module.RoleDto;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IRoleService {

    public Role create(RoleDto roleDto);

    public Role update(Long id, RoleDto roleDto);

    public Map<String, Object> getAllRoles(Pageable pageable);

    public Role getRoleById(Long id);

    public Role getRoleByName(String code);

    public Role inactiveRole(Long id);
}
