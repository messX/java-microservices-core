package com.example.core.role.module;

import com.example.core.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name",unique = true,nullable = false)
    private String roleName;

    @NotNull
    @Column(name = "role_description",nullable = false)
    @Size(min=3, message= "roleDescription must be more than 3 characters")
    @Size(max=200, message= "roleDescription must be less than 200 characters")
    private String roleDescription;

    @NotNull
    @Column(name="is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;
}
