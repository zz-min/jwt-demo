package com.zzmin.jwtdemo22.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "HelloController", description = "HelloController API")
public class HelloController {
    @GetMapping("/hello")
    @Operation(summary = "No Token", description = "token없이 사용가능 API")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/secret")
    public ResponseEntity<String> secret(){
        log.debug("HelloController.secret()");

        return ResponseEntity.ok("secret *_*");
    }

}