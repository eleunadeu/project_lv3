package com.sparta.office.dto;

import lombok.Getter;

@Getter
public class AdminResponseDto {
    private String token;
    private String errorMessage;

    public AdminResponseDto(String token, String errorMessage) {
        this.token = token;
        this.errorMessage = errorMessage;
    }
}
