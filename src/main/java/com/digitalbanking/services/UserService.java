package com.digitalbanking.services;

import com.digitalbanking.dtos.User;
import com.digitalbanking.dtos.UserRequest;
import com.digitalbanking.persistence.entities.UserEntity;
import com.digitalbanking.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public User registerUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .phoneNumber(userRequest.getPhoneNumber())
                .enabled(true)
                .build();

        return userRepository.save(userEntity).toUser();
    }
}
