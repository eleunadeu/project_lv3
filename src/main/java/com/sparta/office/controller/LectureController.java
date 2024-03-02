package com.sparta.office.controller;

import com.sparta.office.dto.LectureRequestDto;
import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.service.LectureService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class LectureController {

    private final LectureService lectureService;

    //강의등록 post lecture
    @PostMapping("/lecture")
    public ResponseEntity<LectureResponseDto> createLecture(@Valid @RequestBody LectureRequestDto requestDto){
        LectureResponseDto responseDto = lectureService.createLecture(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //선택한 강의 수정 put lecture/{id}
    @Secured(AdminRoleEnum.Authority.MANAGER) // 매니저만 수정가능
    @PutMapping("/lecture/{lectureId}") //수정 내용
    public ResponseEntity<LectureResponseDto> modifyLecture(@PathVariable Integer lectureId, @RequestBody LectureRequestDto requestDto){
        LectureResponseDto responseDto = lectureService.modifyLecture(lectureId,requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 선택한 강의 조회 get lecture/{id}
    @GetMapping("/lecture/{lectureId}") //수정 내용
    public ResponseEntity<LectureResponseDto> getLectureInfo(@PathVariable Integer lectureId){
        LectureResponseDto responseDto = lectureService.getLectureInfo(lectureId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 카테고리별 강의 목록 조회 get lectures/{category}
    @GetMapping("/lectures/{category}") //수정 내용
    public ResponseEntity<List<LectureResponseDto>> getLecturesByCategory(@PathVariable String category){
        List<LectureResponseDto> responseDto = lectureService.getLecturesByCategory(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 선택한 강의 삭제  delete lecture/{id}
    @Secured(AdminRoleEnum.Authority.MANAGER) // 매니저만 수정가능
    @DeleteMapping("lecture/{lectureId}")
    ResponseEntity<Integer> deleteLecutre(@PathVariable Integer lectureId){
        Integer deleteId =  lectureService.deleteLecutre(lectureId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
