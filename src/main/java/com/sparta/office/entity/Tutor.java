package com.sparta.office.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tutor_id")
    private Integer id;

    @Column(name="tutor_name",nullable = false)
    private String tutorName; //강사이름

    @Column(name = "career",nullable = false)
    private Integer career; // 경력

    @Column(name="phone",nullable = false)
    private String phone; // 핸드폰번호

    @Column(name = "intro",nullable = false)
    private String Intro; // 강사소개

    @Column(name="company",nullable = false)
    private String company; // 소속회사
}
