package com.example.core.user.module.entity;

import com.example.core.common.enums.UserType;
import com.example.core.common.model.entity.BaseEntity;
import com.example.core.role.module.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @JsonIgnore
    @Column(nullable = false, name = "uuid")
    private String uuid;

    @NotNull
    @Size(min=3, message= "Username must be more than 3 characters")
    @Size(max=200, message= "Username must be less than 200 characters")
    @Column(nullable = false, name = "username")
    private String username;

    @NotNull
    @Email
    @Column(nullable = false, name = "email")
    private String email;

    @NotNull
    @Column(nullable = false, name = "password")
    private String password;

    @NotNull
    @Column(name="is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive = Boolean.TRUE;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
}
