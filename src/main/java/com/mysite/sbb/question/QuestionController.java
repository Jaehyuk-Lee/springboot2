package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    // paging 할 때 1부터 시작하지 않고 0부터 시작한다!!
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // 템플릿의 form 태그에 th:object 속성을 추가했으므로 QuestionController의 GetMapping으로 매핑한 메서드도
    // 다음과 같이 변경해야 오류가 발생하지 않는다. 왜냐하면 question_form.html은 [질문 등록하기] 버튼을 통해 GET 방식으로
    // URL이 요청되더라도 th:object에 의해 QuestionForm 객체가 필요하기 때문이다.
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    // @Valid로 입력된 값을 validation 할 수 있다. (QuestionForm 클래스도 필요)
    // BindingResult가 검증 결과를 저장하고 있음
    // @Valid 어노테이션은 ModelAttribute attr 이 생략된 것과 같다
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}