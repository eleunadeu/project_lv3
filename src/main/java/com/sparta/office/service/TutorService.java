package com.sparta.office.service;

import com.sparta.office.dto.TutorRequestDto;
import com.sparta.office.dto.TutorResponseDto;
import com.sparta.office.entity.Tutor;
import com.sparta.office.repository.TutorRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    @Transactional
    public TutorResponseDto createTutor(TutorRequestDto requestDto) {

        // tutor에 request 로 받은 값 builder 로 보내기

        Tutor tutor = Tutor.builder()
                .tutorName(requestDto.getName())
                .career(requestDto.getCareer())
                .phone(requestDto.getPhone())
                .intro(requestDto.getIntro())
                .company(requestDto.getCompany())
                .build();

        return new TutorResponseDto(tutorRepository.save(tutor));
    }

    // 선택 강사 정보 조회
    public TutorResponseDto getTutorInfo(Integer tutorId) {

        return new TutorResponseDto(tutorRepository.findById(tutorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강사 정보가 없습니다.")
        ));
    }

    public void modifyTutorInfo(TutorRequestDto requestDto, HttpServletRequest request) {
        //user 권한 확인
    }
}
