package com.sparta.office.entity;

import com.sparta.office.repository.LectureRepository;
import com.sparta.office.repository.TutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(value = false)
class TutorTest {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private TutorRepository tutorRepository;



    @Test
    @DisplayName("tutor 테이블 생성 테스트")
    void test1(){
        Tutor tutor = Tutor.builder()
                .tutorName("이지영")
                .career(14)
                .phone("010-1234-1234")
                .intro("대왕유명일타강사")
                .company(" ")
                .build();


        // 저장
        Tutor saveTutor = tutorRepository.save(tutor);

        // 강의 저장 강사 함께
        Lecture lecture = Lecture.builder()
                .lectureName("수학")
                .intro("이해 쏙쏙 수학")
                .category("수학")
                .tutor(saveTutor)
                .build();

        Lecture saveLecture = lectureRepository.save(lecture);

    }




}