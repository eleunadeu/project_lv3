package com.sparta.office.service;

import com.sparta.office.dto.LectureRequestDto;
import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.entity.Lecture;
import com.sparta.office.entity.Tutor;
import com.sparta.office.repository.LectureRepository;
import com.sparta.office.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto requestDto) {
        // 강사 조회하기
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId()).orElseThrow( //강사 정보 없으면 실패
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강사 정보가 없습니다.")
        );
        // lecture에 값 넣어주기
        Lecture lecture = Lecture.builder()
                .lectureName(requestDto.getLectureName())
                .price(requestDto.getPrice())
                .intro(requestDto.getIntro())
                .category(requestDto.getIntro())
                .tutor(tutor) // 외래키 넣기
                .build();

        // db에 저장
        return new LectureResponseDto(lectureRepository.save(lecture));
    }
}
