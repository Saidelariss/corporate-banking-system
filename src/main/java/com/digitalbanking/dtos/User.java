package com.digitalbanking.dtos;

import com.digitalbanking.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private Role role;
    private String phoneNumber;
}
