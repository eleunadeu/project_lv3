package com.sparta.office.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TutorRequestDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @Positive(message = "양수만 입력 하세요.")
    private Integer career;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "올바른 전화번호를 입력하세요.")
    private String phone;

    @NotBlank(message = "소개 내용을 입력해 주세요.")
    private String intro;

    @NotBlank(message = "회사명을 입력해주세요.")
    private String company;

}

