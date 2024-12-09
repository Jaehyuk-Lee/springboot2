package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    private LocalDateTime modifyDate;

    // 1:N 관계에서 "1" 쪽에 mappedBy를 써줘야 함. 지우는거에 Cascade도 걸고
    // Question만 일으면 answer도 다 같이 가져옴.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    // User는 예약어로 쓰이는 곳이 있어서 다른 이름으로 만드는걸 추천
    @ManyToOne
    private SiteUser author;

    // ManyToMany를 쓰게 되면 테이블을 새로 만든다.
    // Set은 같은 사람이 같은 게시글을 여러개 추천하지 못하도록 하기 위해 사용했음. 집합은 원소가 중복될 수 없다.
    @ManyToMany
    Set<SiteUser> voter;
}