package com.sparta.office.service;

import com.sparta.office.entity.Admin;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AdminRepository adminRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String email, String password) {
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        return adminOptional.map(admin -> passwordMatches(password, admin.getPassword())).orElse(false);
    }

    private boolean passwordMatches(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }

    public Optional<AdminRoleEnum> getAdminRoleByEmail(String email) {
        return adminRepository.findByEmail(email)
                .map(Admin::getRole);
    }
}
