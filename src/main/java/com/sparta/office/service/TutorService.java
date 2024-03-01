package com.sparta.office.service;

import com.sparta.office.dto.TutorRequestDto;
import com.sparta.office.dto.TutorResponseDto;
import com.sparta.office.entity.Tutor;
import com.sparta.office.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
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
}
