package com.sparta.office.controller;

import com.sparta.office.dto.AdminRequestDto;
import com.sparta.office.dto.AdminResponseDto;
import com.sparta.office.dto.LoginRequestDto;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.jwt.JwtUtil;
import com.sparta.office.service.AdminService;
import com.sparta.office.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    @PostMapping("admin/signup")
    public String signup(@Valid AdminRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/admin/signup";
        }

        // 회원가입 처리
        adminService.signup(requestDto);

        return "redirect:/api/admin/login-page";
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AdminResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        Optional<AdminRoleEnum> roleOptional = authenticationService.getAdminRoleByEmail(requestDto.getEmail());
        if (roleOptional.isPresent()) {
            AdminRoleEnum role = roleOptional.get();
            String token = jwtUtil.createToken(requestDto.getEmail(), role);
            AdminResponseDto response = new AdminResponseDto(token, "");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // 사용자가 없거나 권한을 찾을 수 없는 경우
            String token = "";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AdminResponseDto(token, "Error: Authentication failed"));
        }
    }
}
