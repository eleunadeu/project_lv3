package com.sparta.office.dto;

import com.sparta.office.entity.Tutor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TutorResponseDto {
    private Integer id;
    private String tutorName;
    private Integer career;
    private String phone;
    private String intro;
    private String company;


    public TutorResponseDto(Tutor tutor) {
        this.id = tutor.getId();
        this.tutorName = tutor.getTutorName();
        this.career = tutor.getCareer();
        this.phone = tutor.getPhone();
        this.intro = tutor.getIntro();
        this.company = tutor.getCompany();
    }


}
