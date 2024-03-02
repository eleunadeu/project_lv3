package com.sparta.office.controller;

import com.sparta.office.dto.AdminLectureList;
import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.dto.TutorRequestDto;
import com.sparta.office.dto.TutorResponseDto;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.service.TutorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TutorController {
    //- 로그인을 통해 발급받은 JWT가 함께 요청됩니다.
    //- 관리자만 강사 등록이 가능합
    // 수정, 삭제 : manager 만 가능
    private final TutorService tutorService;


    //강사 등록 post tutor
    @PostMapping("/tutor")
    public ResponseEntity<TutorResponseDto> createTutor(@Valid @RequestBody TutorRequestDto requestDto){
        TutorResponseDto responseDto = tutorService.createTutor(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED); // 강의 등록 상태코드 같이 보내기
    }


    //선택한 강사 정보 조회 get tutor/{id}
    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<TutorResponseDto> getTutorInfo(@PathVariable Integer tutorId){
        TutorResponseDto responseDto = tutorService.getTutorInfo(tutorId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK); // 정상 상태코드 같이 보내기
    }


    // 선택한 강사 정보 수정 put tutor/{id} -> admin 만 할 수 있음
    @Operation(summary = "강사 정보 수정",description = "강사 경력(년차), 회사, 전화번호, 소개를 수정합니다.")
    @Secured(AdminRoleEnum.Authority.MANAGER)
    @PutMapping("/tutor/{tutorId}") //수정 내용
    public ResponseEntity<TutorResponseDto> modifyTutorInfo(@PathVariable Integer tutorId,@RequestBody TutorRequestDto requestDto){
        TutorResponseDto responseDto = tutorService.modifyTutorInfo(tutorId,requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 선택한 강사가 촬영한 강의 목록 조회 get tutor/{id}
    @GetMapping("/tutor/{tutorId}/lectures")
    @Operation(summary = "강사의 촬영 강의 목록 조회",description = "선택한 강사의 촬영 강의 목록을 조회합니다. 등록일 기준 내림차순으로 조회됩니다.")
    public ResponseEntity<List<AdminLectureList>> getTutorLecutreList(@PathVariable Integer tutorId){

        List<AdminLectureList> responseDto = tutorService.getTutorLecutreList(tutorId);
        if (!responseDto.isEmpty()) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 강사의 강의목록이 없습니다");
        }
    }


    // 선택한 강사 삭제 delete tutor{id}
    @Operation(summary = "강사 삭제 기능",description = "강사를 삭제합니다.")
    @Secured(AdminRoleEnum.Authority.MANAGER)
    @DeleteMapping("/tutor/{tutorId}")
    ResponseEntity<Integer> deleteTutor(@PathVariable Integer tutorId){
        Integer deleteId =  tutorService.deleteTutor(tutorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
