package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column(columnDefinition = "TEXT")
    @Lob
    private String content;

    private LocalDateTime createDate;

    @ManyToOne // Question과 Answer는 N:1 관계
    private Question question;
}