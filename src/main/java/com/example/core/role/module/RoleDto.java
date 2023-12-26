package com.example.core.role.module;

import com.example.core.common.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleDto extends BaseEntity {


    private Long id;

    @NotNull
    private String roleName;

    @NotNull
    @Size(min=3, message= "roleDescription must be more than 3 characters")
    @Size(max=200, message= "roleDescription must be less than 200 characters")
    private String roleDescription;

    private Boolean isActive;
}
