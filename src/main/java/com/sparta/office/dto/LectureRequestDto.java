package com.sparta.office.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LectureRequestDto {
    @NotBlank(message = "강의명을 입력해 주세요.")
    private String lectureName;

    @Min(value = 100, message = "100 이상 입력해 주세요")
    private int price;

    @NotBlank(message = "강의 소개를 입력해 주세요.")
    private String intro;

    @NotBlank(message = "카테고리를 입력해주세요.")
    private String category;

    @NotNull(message = "강사ID를 입력해 주세요.")
    private Integer tutorId;

}
