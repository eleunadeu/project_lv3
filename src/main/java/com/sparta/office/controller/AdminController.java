//package com.sparta.office.controller;
//
//import com.sparta.office.dto.AdminRequestDto;
//import com.sparta.office.service.AdminService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class AdminController {
//
////    private final AdminService adminService;
//
//    @PostMapping("/api/admin/signup")
//    public String signup(@Valid AdminRequestDto requestDto, BindingResult bindingResult) {
//        // Validation 예외처리
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        if (!fieldErrors.isEmpty()) {
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
//            }
//            return "redirect:/api/admin/signup";
//        }
//
//        // 회원가입 처리
////        adminService.signup(requestDto);
//
//        return "redirect:/api/admin/login-page";
//    }
//}
