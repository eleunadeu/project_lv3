package com.sparta.office.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @Email
    private String email;

    private String password;

    public LoginRequestDto() {
        // 기본 생성자
    }

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
