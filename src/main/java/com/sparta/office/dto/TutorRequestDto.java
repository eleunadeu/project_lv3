package com.sparta.office.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TutorRequestDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    @Schema(description = "강사 이름", example = "김강사")
    private String name;

    @Positive(message = "양수만 입력 하세요.")
    @Schema(description = "경력", example = "13")
    private Integer career;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "올바른 전화번호를 입력하세요.")
    @Schema(description = "핸드폰번호", example = "010-0000-0000")
    private String phone;

    @NotBlank(message = "소개 내용을 입력해 주세요.")
    @Schema(description = "강사 소개 내용", example = "이해 쏙쏙 최고 강사")
    private String intro;

    @NotBlank(message = "회사명을 입력해주세요.")
    @Schema(description = "강사 소개 내용", example = "항해99")
    private String company;

}

