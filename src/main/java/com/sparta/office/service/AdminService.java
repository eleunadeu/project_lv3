package com.sparta.office.service;

import com.sparta.office.dto.AdminRequestDto;
import com.sparta.office.entity.Admin;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN -> 환경변수 값으로 변경 예정
    private static final Map<String, AdminRoleEnum> tokenRoleMap = Map.of(
            "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC", AdminRoleEnum.STAFF,
            "0aHgTBcXukeZygoCAAABnvxRVklrnYxKZ", AdminRoleEnum.MANAGER
    );

    /// 비밀번호 검증 패턴
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_~]).{8,15}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);


    public void signup(AdminRequestDto requestDto) {
        // 비밀번호 검증
        String password = requestDto.getPassword();
        if (!pattern.matcher(password).matches()) {
            throw new IllegalArgumentException("비밀번호는 최소 8자리에서 최대 15자리이며, " +
                    "영어 대소문자(a~zA~Z), 숫자, 특수문자 !@#$%^&*()_~만 사용 가능합니다.");
        }
        // 비밀번호 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        // email 중복 확인
        String email = requestDto.getEmail();
        Optional<Admin> checkEmail = adminRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 관리자 여부 확인 및 권한 부여
        AdminRoleEnum role = determineRoleByToken(requestDto.getAdminToken());

        // 부서 정보
        String team = requestDto.getTeam();

        // 관리자 등록
        Admin admin = new Admin(email, password, team, role);
        adminRepository.save(admin);
    }

    // 관리자 여부 확인 및 권한 등급 확인 메서드
    public AdminRoleEnum determineRoleByToken(String adminToken) {
        AdminRoleEnum role = tokenRoleMap.get(adminToken);
        if (role == null) {
            throw new IllegalArgumentException("관리자 권한이 유효하지 않아 등록이 불가능합니다.");
        }
        return role;
    }
}
