package com.sparta.office.service;

import com.sparta.office.dto.LectureRequestDto;
import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.dto.TutorResponseDto;
import com.sparta.office.entity.Lecture;
import com.sparta.office.entity.Tutor;
import com.sparta.office.repository.LectureRepository;
import com.sparta.office.repository.TutorRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    @Transactional
    public LectureResponseDto modifyLectureInfo(Integer lectureId, LectureRequestDto requestDto, HttpServletRequest request) {
        //filter에서 admin으로 넣어뒀다고 가정하고 진행
//        Admin admin = (Admin) request.getAttribute("admin");
//
//        //권한 확인
//        if (admin.getRole() != AdminRoleEnum.MANAGER) {
//            // 매니저 아니니까 접근 불가
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 불가");
//        }

        // 강의가 있으면 정보 수정 // 강의명, 가격, 소개, 카테고리 수정
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow( // entitynotfound
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강의 정보가 없습니다.")
        );

        // 수정된 내용 업데이트
        lecture.update(requestDto.getLectureName(),
                requestDto.getPrice(),
                requestDto.getIntro(),
                requestDto.getCategory()); // 업데이트 되면 덜티체킹하면서 알아서 업데이트 될거임

        // 업데이트 된 내용 리턴
        return new LectureResponseDto(lecture);
    }
}
