package com.sparta.office.dto;

import com.sparta.office.entity.Lecture;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
@Data
@Getter
public class LectureResponseDto {
    private Integer id;
    private String lectureName;
    private int price;
    private String intro;
    private String category;
    private Integer tutorId;
    private LocalDate registerAt;

    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.intro = lecture.getIntro();
        this.category = lecture.getCategory();
        this.tutorId = lecture.getTutor().getId();
        this.registerAt = lecture.getRegisterAt();
    }
}
