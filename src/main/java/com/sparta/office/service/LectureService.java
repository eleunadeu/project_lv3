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

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto requestDto) {
        // 강사 조회하기
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId()).orElseThrow( //강사 정보 없으면 실패
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강사 정보가 없습니다."));
        // lecture에 값 넣어주기
        Lecture lecture = Lecture.builder().lectureName(requestDto.getLectureName()).price(requestDto.getPrice()).intro(requestDto.getIntro()).category(requestDto.getCategory()).tutor(tutor) // 외래키 넣기
                .build();

        // db에 저장
        return new LectureResponseDto(lectureRepository.save(lecture));
    }

    @Transactional
    public LectureResponseDto modifyLecture(Integer lectureId, LectureRequestDto requestDto) {

        // 강의가 있으면 정보 수정 // 강의명, 가격, 소개, 카테고리 수정
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow( // entitynotfound
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강의 정보가 없습니다."));

        // 수정된 내용 업데이트
        lecture.update(requestDto.getLectureName(), requestDto.getPrice(), requestDto.getIntro(), requestDto.getCategory()); // 업데이트 되면 덜티체킹하면서 알아서 업데이트 될거임

        // 업데이트 된 내용 리턴
        return new LectureResponseDto(lecture);
    }


    // 선택한 강의 정보조회
    public LectureResponseDto getLectureInfo(Integer lectureId) {

        return new LectureResponseDto(lectureRepository.findById(lectureId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 강의 정보입니다!")));
    }

    public List<LectureResponseDto> getLecturesByCategory(String category) {
        List<LectureResponseDto> responseDtoList = lectureRepository.findAllByCategoryOrderByRegisterAtDesc(category).stream().map(LectureResponseDto::new).toList();
        return responseDtoList;
    }

    public Integer deleteLecutre(Integer lectureId) {
        //filter에서 admin으로 넣어뒀다고 가정하고 진행

        //매니저라고 하고 삭제 진행
        //유무 확인
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 강의 정보입니다!"));

        //있으니까 정상삭제하고 삭제한 번호 반환
        lectureRepository.delete(lecture);
        return lectureId;
    }
}
