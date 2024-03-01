package com.sparta.office.controller;

import com.sparta.office.dto.TutorRequestDto;
import com.sparta.office.dto.TutorResponseDto;
import com.sparta.office.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutor")
public class TutorController {
    //- 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
    //- 관리자만 강사 등록이 가능합
    // 수정, 삭제 : manager 만 가능
    private final TutorService tutorService;


    //강사 등록 post tutor
    @PostMapping("/")
    public ResponseEntity<TutorResponseDto> createTutor(@RequestBody TutorRequestDto requestDto){
        TutorResponseDto responseDto = tutorService.createTutor(requestDto);
        return null;
    }

    //선택한 강사 정보 조회 get tutor/{id}


    // 선택한 강사 정보 수정 put tutor/{id}

    // 선택한 강사가 촬영한 강의 목록 조회 get tutor/{id}

    // 선택한 강사 삭제 delete tutor{id}

}
