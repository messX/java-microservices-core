package com.example.core.role.service;

import com.example.core.common.exception.InvalidIdException;
import com.example.core.role.module.Role;
import com.example.core.role.module.RoleDto;
import com.example.core.role.module.RoleMapper;
import com.example.core.role.repository.RoleRepository;
import com.example.core.util.PageUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class RoleService implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PageUtils<Role> pageUtils;

    @Transactional
    @Override
    public Role create(RoleDto roleDto) {
        Role role = roleMapper.RoleDtoToRole(roleDto);
        role = roleRepository.save(role);
        log.info(String.format("Created role %s", role.getRoleName()));
        return role;
    }

    @Transactional
    @Override
    public Role update(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> {
         log.error(String.format("Invalid role id %d", id));
         return new InvalidIdException();
        });
        roleMapper.updateRoleFromRoleDto(roleDto, role);
        role = roleRepository.save(role);
        log.info(String.format("Updated role %s", role.getRoleName()));
        return role;
    }

    @Override
    public Map<String, Object> getAllRoles(Pageable pageable) {
        Page<Role> pagedData = roleRepository.findAll(pageable);
        return pageUtils.convertToResponse(pagedData);
    }

    @Override
    public Role getRoleById(Long id) {
        Role role;
        role = roleRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("Invalid role id %d", id));
            return new InvalidIdException();
        });
        log.info(String.format("Returning data for role %s", role.getRoleName()));
        return role;
    }

    @Override
    public Role getRoleByName(String code) {
        Role role;
        role = roleRepository.findByRoleName(code).orElseThrow(() -> new RuntimeException("Invalid role name"));
        log.info(String.format("Returning data for role %s", role.getRoleName()));
        return role;
    }

    @Override
    public Role inactiveRole(Long id) {
        return null;
    }
}
