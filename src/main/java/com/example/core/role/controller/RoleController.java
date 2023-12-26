package com.example.core.role.controller;


import com.example.core.role.module.Role;
import com.example.core.role.module.RoleDto;
import com.example.core.role.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("${base.url}/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;


    @GetMapping(name="getAllRoles", path="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllRoles(@RequestParam(defaultValue = "${default.page.number}") final Integer pageNumber,
                                                           @RequestParam(defaultValue = "${default.page.size}") final Integer pageSize){
        log.info(String.format("Getting all role for page %d and size %d", pageNumber, pageSize));
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(roleService.getAllRoles(paging));
    }

    @GetMapping(name="getRole", path = "/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("roleId") Long roleId){
        log.info(String.format("Getting role data for %d", roleId));
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @PostMapping(name="createRole", path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> createRole(@RequestBody @Validated RoleDto roleDto){
        log.info(String.format("creating a new role for role name %s", roleDto.getRoleName()));
        return new ResponseEntity<Role>(roleService.create(roleDto), HttpStatus.CREATED);
    }

    @PutMapping(name="updateRole", path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody @Validated RoleDto roleDto){
        log.info(String.format("updating role %s", roleDto.getRoleName()));
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }


}
