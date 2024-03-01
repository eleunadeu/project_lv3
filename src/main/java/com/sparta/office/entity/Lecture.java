package com.sparta.office.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lecture")
@EntityListeners(AuditingEntityListener.class)
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lecture_id")
    private Integer id; // 강의 고유 번호

    @Column(name="lecture_name")
    private String lectureName; // 강의 이름

    @Column(name="lecture_intro")
    private String intro; //강의소개

    @Enumerated(EnumType.STRING)
    @Column(name="category",nullable = false)
    private Category category; // 카테고리  enum으로 관리

    //fk 설정
    @ManyToOne
    @JoinColumn(name="tutor_id")
    private Tutor tutor; //강사 외래키 설정

    @CreatedDate
    @Column(name="register_at",updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate registerAt; // 강의 등록일

}
