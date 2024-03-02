package com.sparta.office;

import com.sparta.office.entity.Admin;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.repository.AdminRepository;
import com.sparta.office.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateSuccess() {
        // Given
        String email = "test@example.com";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";

        Admin admin = new Admin(email, encodedPassword, "TEAM", AdminRoleEnum.STAFF);
        when(adminRepository.findByEmail(email)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // When
        boolean result = authenticationService.authenticate(email, rawPassword);

        // Then
        assertTrue(result, "Authentication should succeed with correct credentials.");
    }

    @Test
    void authenticateFailureDueToIncorrectPassword() {
        // Given
        String email = "test@example.com";
        String rawPassword = "wrongPassword";
        String encodedPassword = "encodedPassword";

        Admin admin = new Admin(email, encodedPassword, "TEAM", AdminRoleEnum.STAFF);
        when(adminRepository.findByEmail(email)).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        // When
        boolean result = authenticationService.authenticate(email, rawPassword);

        // Then
        assertFalse(result, "Authentication should fail with incorrect password.");
    }

    @Test
    void authenticateFailureDueToNonExistentEmail() {
        // Given
        String email = "nonexistent@example.com";
        when(adminRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        boolean result = authenticationService.authenticate(email, "anyPassword");

        // Then
        assertFalse(result, "Authentication should fail with non-existent email.");
    }
}

