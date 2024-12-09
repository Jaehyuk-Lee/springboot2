package com.mysite.sbb.question;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity // 이걸 걸면 DB에 이 구조에 맞는 테이블을 알아서 만들어준다.
public class ArticleDTO implements Serializable {
    // Entity를 쓸 때는 Id가 반드시 있어야 함
    @Id
    private long no;
    private String name;
    private String title;
    private String content;
    private Date regdate;
    private int readcount;
    private String password;

}