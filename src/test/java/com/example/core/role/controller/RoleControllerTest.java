package com.example.core.role.controller;

import com.example.core.role.module.Role;
import com.example.core.role.repository.RoleRepository;
import com.example.core.role.service.IRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @MockBean
    IRoleService roleService;

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateRole() throws Exception {
        Role role = getNewRole();

        mockMvc.perform(post("/api/v1/role/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnRole() throws Exception {
        long id = 1L;
        Role role = getNewRole();
        when(roleService.getRoleById(id)).thenReturn(role);
        mockMvc.perform(get("/api/v1/role/1"))
                .andDo( result -> {
                   log.info("printing res"+ result.getResponse().getContentAsString());
                   log.info("Printed result");
                })
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(status().isOk());
    }

    private Role getNewRole(){
        return Role.builder()
                .roleDescription("Test Role")
                .roleName("Test")
                .id(1L)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

    }

}
