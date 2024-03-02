package com.sparta.office.controller;

import com.sparta.office.dto.AdminRequestDto;
import com.sparta.office.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid AdminRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Validation 에러 메시지 처리
            return ResponseEntity.badRequest().body("회원가입 양식의 오류를 확인해주세요.");
        }

        // 회원가입 처리
        adminService.signup(requestDto);

        // HTTP 상태 코드 200 반환과 함께 메시지 포함
        return ResponseEntity.ok("회원가입에 성공했습니다. 로그인 페이지로 이동해 로그인을 진행해주세요.");
    }
}
