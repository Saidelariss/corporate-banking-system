package com.digitalbanking.api;

import com.digitalbanking.dtos.AuthenticationRequest;
import com.digitalbanking.dtos.User;
import com.digitalbanking.dtos.UserRequest;
import com.digitalbanking.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final UserService userService;


    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        userService.authenticate(authenticationRequest);
        return ResponseEntity.ok().build();
    }
}
