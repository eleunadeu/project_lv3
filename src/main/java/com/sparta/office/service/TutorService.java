package com.sparta.office.service;

import com.sparta.office.dto.AdminLectureList;
import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.dto.TutorRequestDto;
import com.sparta.office.dto.TutorResponseDto;
import com.sparta.office.entity.Admin;
import com.sparta.office.entity.AdminRoleEnum;
import com.sparta.office.entity.Lecture;
import com.sparta.office.entity.Tutor;
import com.sparta.office.repository.LectureRepository;
import com.sparta.office.repository.TutorRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final LectureRepository lectureRepository;

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


    // dirty checking
    @Transactional
    public TutorResponseDto modifyTutorInfo(Integer tutorId, TutorRequestDto requestDto, HttpServletRequest request) {
        //filter에서 admin으로 넣어뒀다고 가정하고 진행
//        Admin admin = (Admin) request.getAttribute("admin");
//
//        //권한 확인
//        if (admin.getRole() != AdminRoleEnum.MANAGER) {
//            // 매니저 아니니까 접근 불가
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 불가");
//        }

        // 강사가 있으면 정보 수정 // 연차, 회사, 핸드폰번호, 소개 수정
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow( // entitynotfound
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강사 정보가 없습니다.")
        );

        // 수정된 내용 업데이트
        tutor.update(requestDto.getCareer(),
                    requestDto.getCompany(),
                    requestDto.getPhone(),
                    requestDto.getIntro()); // 업데이트 되면 덜티체킹하면서 알아서 업데이트 될거임

        // 업데이트 된 내용 리턴
        return new TutorResponseDto(tutor);
    }

    // 선택 강사 정보 조회
    public TutorResponseDto getTutorInfo(Integer tutorId) {
        return new TutorResponseDto(tutorRepository.findById(tutorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강사 정보가 없습니다.")
        ));
    }


    //선택한 강사가 촬영한 강의 목록, 등록일 기준 내림차순
    @Transactional(readOnly = true)
    public List<AdminLectureList> getTutorLecutreList(Integer tutorId) {
        // 오름 차순으로 가져오기
        List<Lecture> lecture = lectureRepository.findByTutorIdOrderByRegisterAtDesc(tutorId);
        return lecture.stream().map(AdminLectureList::new).toList();
    }

    public Integer deleteTutor(Integer tutorId, HttpServletRequest request) {
        //filter에서 admin으로 넣어뒀다고 가정하고 진행
//        Admin admin = (Admin) request.getAttribute("admin");
//
//        //권한 확인
//        if (admin.getRole() != AdminRoleEnum.MANAGER) {
//            // 매니저 아니니까 접근 불가
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 불가");
//        }
        //매니저라고 하고 삭제 진행
        //유무 확인
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 강의 정보입니다!")
        );

        //있으니까 정상삭제하고 삭제한 번호 반환
        tutorRepository.delete(tutor);
        return tutorId;
    }

}
