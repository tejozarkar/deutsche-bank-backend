package com.deutsche.Blog.model;

import lombok.Data;

@Data
public class LoginUser {

    private String username;
    private String password;
    private String securityAnswer;

}