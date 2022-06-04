package com.deutsche.Blog.controller;

import com.deutsche.Blog.config.TokenProvider;
import com.deutsche.Blog.model.*;
import com.deutsche.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/security-question/{username}")
    public ResponseEntity<?> getSecurityQuestion(@PathVariable String username){
        User user = userService.findOne(username);
        return ResponseEntity.ok(new SecurityQuestion(user.getSecurityQuestion()));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        User user = userService.findOne(loginUser.getUsername());
        if(user.getSecurityAnswer().equalsIgnoreCase(loginUser.getSecurityAnswer())){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(new AuthToken(token));
        }
       return ResponseEntity.status(401).body(new Message("Incorrect Security Answer"));
    }

    @GetMapping("/me")
    public User getUser(Authentication authentication){
        return userService.findOne(authentication.getName());
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminping")
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userping")
    public String userPing(){
        return "Any User Can Read This";
    }

}
