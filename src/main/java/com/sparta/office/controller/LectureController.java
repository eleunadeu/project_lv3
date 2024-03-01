package com.sparta.office.controller;

import com.sparta.office.dto.LectureRequestDto;
import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    //강의등록 post lecture
    @PostMapping("/lecture")
    public ResponseEntity<LectureResponseDto> createLecture(@Valid @RequestBody LectureRequestDto requestDto){
        LectureResponseDto responseDto = lectureService.createLecture(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    //선택한 강의 수정 put lecture/{id}

    // 선택한 강의 조회 get lecture/{id}

    // 카테고리별 강의 목록 조회 get lectures/{category}

    // 선택한 강의 삭제  delete lecture/{id}

}
