package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private  final QuestionService questionService;
    private  final AnswerService answerService;
    @PostMapping("/create/{id}")
    public String create (@PathVariable("id") Integer id, @RequestParam("content") String content) {
        Question question  = this.questionService.getQuestion(id);
        this.answerService.create(question,content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
