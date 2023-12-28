package com.sbb.qna.question;

import com.sbb.qna.answer.AnswerController;
import com.sbb.qna.answer.AnswerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list (Model model) {
        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Integer id,Model model) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam("subject") String subject,@RequestParam("content") String content){
        this.questionService.create(subject,content);
        return "redirect:/question/list";
    }
}

