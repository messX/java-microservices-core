package com.example.core.role.module;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role RoleDtoToRole(RoleDto roleDto);
    RoleDto RoleToRoleDto(Role role);
    void updateRoleFromRoleDto(RoleDto roleDto, @MappingTarget Role role);
}
