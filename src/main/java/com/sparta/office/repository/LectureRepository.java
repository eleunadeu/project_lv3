package com.sparta.office.repository;

import com.sparta.office.dto.LectureResponseDto;
import com.sparta.office.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    List<Lecture> findByTutorIdOrderByRegisterAtDesc(Integer tutorId);

    List<Lecture> findAllByCategoryOrderByRegisterAtDesc(String category);
}
