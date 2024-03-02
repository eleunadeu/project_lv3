package com.sparta.office;

import com.sparta.office.dto.AdminRequestDto;
import com.sparta.office.entity.Admin;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.repository.AdminRepository;
import com.sparta.office.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private AdminRequestDto validRequestDto;
    private AdminRequestDto invalidRequestDto;

    @BeforeEach
    void setUp() {
        validRequestDto = new AdminRequestDto("test@example.com", "ValidPass1!", "TEAM", "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC");
        invalidRequestDto = new AdminRequestDto("test@example.com", "invalid", "TEAM", "invalidToken");

        when(adminRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
    }

    @Test
    void signupSuccessful() {
        assertDoesNotThrow(() -> adminService.signup(validRequestDto));
    }

    @Test
    void signupWithInvalidPassword() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> adminService.signup(invalidRequestDto));
        assertTrue(thrown.getMessage().contains("비밀번호는 최소 8자리에서 최대 15자리이며"));
    }

    @Test
    void signupWithDuplicateEmail() {
        when(adminRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new Admin()));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> adminService.signup(validRequestDto));
        assertTrue(thrown.getMessage().contains("중복된 Email 입니다."));
    }

    @Test
    void determineRoleByValidToken() {
        AdminRoleEnum role = adminService.determineRoleByToken("AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC");
        assertEquals(AdminRoleEnum.STAFF, role);
    }

    @Test
    void determineRoleByInvalidToken() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> adminService.determineRoleByToken("invalidToken"));
        assertTrue(thrown.getMessage().contains("관리자 권한이 유효하지 않아 등록이 불가능합니다."));
    }
}
