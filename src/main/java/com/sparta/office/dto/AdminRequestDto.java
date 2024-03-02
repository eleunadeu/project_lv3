package com.sparta.office.dto;

import com.sparta.office.entity.AdminRoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminRequestDto {

    @Email
    @NotBlank
    @Schema(description = "이메일", example = "email@email.com")
    private String email;
    @NotBlank
    @Schema(description = "비밀번호", example = "p@assw0rd!2")
    private String password;
    @NotBlank
    @Schema(description = "부서명", example = "개발")
    private String team;

    @Schema(description = "권한", example = "true")
    private boolean admin = false;
    @Schema(description = "테스트용 토큰", example = "0aHgTBcXukeZygoCAAABnvxRVklrnYxKZ")
    private String adminToken;

    public AdminRequestDto(String email, String password, String team, String adminToken) {
        this.email = email;
        this.password = password;
        this.team = team;
        this.adminToken = adminToken;
    }
}
