package com.sparta.office.dto;

import com.sparta.office.entity.AdminRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String team;
    @NotBlank
    private AdminRoleEnum role;
    private boolean admin = false;
    private String adminToken;

    public AdminRequestDto(String email, String password, String team, String adminToken) {
        this.email = email;
        this.password = password;
        this.team = team;
        this.adminToken = adminToken;
    }
}
