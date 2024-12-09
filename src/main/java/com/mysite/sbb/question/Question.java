package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String subject;

    // @Column(columnDefinition = "TEXT") // H2 / MySQL같은 다른 데이터베이스 쓸 때
    @Lob
    @Column(nullable = false)
    private String content;

    private LocalDateTime createDate;

    // 1:N 관계에서 "1" 쪽에 mappedBy를 써줘야 함. 지우는거에 Cascade도 걸고
    // Question만 일으면 answer도 다 같이 가져옴.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}