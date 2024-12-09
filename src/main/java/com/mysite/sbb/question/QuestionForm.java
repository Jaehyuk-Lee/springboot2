package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    // validation library로 어노테이션 적용.
    // 개별적으로 적는 것보다는 xml 파일로 적는게 다국어 처리에 좋지만 이번에는 간단히 배우기 위해 직접 한국어 메시지 작성
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}