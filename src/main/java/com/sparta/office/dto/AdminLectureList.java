package com.sparta.office.dto;

import com.sparta.office.entity.Lecture;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AdminLectureList { // 관리자가 조회한 강의 목록
    private Integer tutorId;
    private String tutorName;
    private Integer lectureId;
    private String lectureName;
    private String category;
    private LocalDate registerAt;


    public AdminLectureList(Lecture lecture) {
        
        this.tutorId = lecture.getTutor().getId();
        this.tutorName = lecture.getTutor().getTutorName();

        this.lectureName = lecture.getLectureName();
        this.lectureId = lecture.getId();
        this.category = lecture.getCategory();
        this.registerAt = lecture.getRegisterAt();
    }
}
