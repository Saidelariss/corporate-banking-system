package com.digitalbanking.persistence.entities;

import com.digitalbanking.enums.Role;
import com.digitalbanking.persistence.entities.base.AuditEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class UserEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled = false;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String phoneNumber;
    @OneToMany(mappedBy = "owner")
    private List<AccountEntity> accounts;
}
