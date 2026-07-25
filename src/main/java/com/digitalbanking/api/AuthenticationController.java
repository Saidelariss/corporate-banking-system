package com.digitalbanking.api;

import com.digitalbanking.dtos.User;
import com.digitalbanking.dtos.UserRequest;
import com.digitalbanking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final UserService userService;


    @PostMapping("/register")
    public User registerUser(UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }
}
