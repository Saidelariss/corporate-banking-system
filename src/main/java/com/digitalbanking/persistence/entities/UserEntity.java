package com.digitalbanking.persistence.entities;

import com.digitalbanking.dtos.User;
import com.digitalbanking.enums.Role;
import com.digitalbanking.persistence.entities.base.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public User toUser() {
        return User.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.firstName)
                .phoneNumber(this.phoneNumber)
                .role(this.role)
                .enabled(this.enabled)
                .build();
    }
}
