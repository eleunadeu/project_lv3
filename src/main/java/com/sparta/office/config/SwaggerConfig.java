package com.sparta.office.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;

import java.util.Arrays;

@OpenAPIDefinition(
        info = @Info(title = "📚 스파르타 백오피스 서버 만들기 API 명세서",
                description = "민가람 | 박용운",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

}
