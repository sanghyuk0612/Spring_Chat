package com.inu.hackerton.SpringProject.dto;

import lombok.Getter;

@Getter
public class SignUpDTO {
    // Getter, Setter 추가
    private String username;
    private String password;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}