package com.deutsche.Blog.model;

import lombok.Data;

@Data
public class UserDto {
    
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String securityQuestion;
    private String securityAnswer;

    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setSecurityQuestion(securityQuestion);
        user.setSecurityAnswer(securityAnswer);
        return user;
    }
    
}