package com.sparta.office.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerLoginController {

    @Operation(summary = "로그인", description = "JWT 토큰을 발급받기 위한 로그인 API")
    @PostMapping("/api/admin/login")
    public void fakeLogin() {
        // 이 메서드는 실제 로직을 구현하지 않습니다. Swagger 문서용입니다.
    }
}
