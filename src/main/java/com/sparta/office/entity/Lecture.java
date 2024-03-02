package com.sparta.office.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lecture_id")
    private Integer id; // 강의 고유 번호

    @Column(name="lecture_name", nullable = false)
    private String lectureName; // 강의 이름

    @Column(name="price", nullable = false)
    private int price;

    @Column(name="lecture_intro", nullable = false)
    private String intro; //강의소개


    @Column(name="category",nullable = false)
    private String category;

    //fk 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tutor_id")
    private Tutor tutor; //강사 외래키 설정

    @CreatedDate
    @Column(name="register_at",updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate registerAt; // 강의 등록일

    @Builder
    public Lecture(String lectureName, int price, String intro, String category, Tutor tutor){
        this.lectureName=lectureName;
        this.price=price;
        this.intro=intro;
        this.category=category;
        this.tutor=tutor;
    }

    public void update(String lectureName, int price, String intro, String category) {
        this.lectureName = lectureName;
        this.price = price;
        this.intro = intro;
        this.category = category;
    }
}
