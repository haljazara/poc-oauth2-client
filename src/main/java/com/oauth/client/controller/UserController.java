package com.oauth.client.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @PreAuthorize("hasRole('role-name')")
    @GetMapping(value = "/api/user/info")
    public ResponseEntity<String> info(@AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>("Welcome " + principal.getName(), HttpStatus.OK);
    }
}