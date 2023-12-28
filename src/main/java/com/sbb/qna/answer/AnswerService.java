package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    final private AnswerRepository answerRepository;
    public void create(Question question, String content) {

        Answer a = new Answer();
        a.setQuestion(question);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());

        this.answerRepository.save(a);
    }
}
