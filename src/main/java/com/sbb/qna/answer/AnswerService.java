package com.sbb.qna.answer;

import com.sbb.qna.question.Question;
import com.sbb.qna.siteuser.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AnswerService {

    final private AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {

        Answer a = new Answer();
        a.setQuestion(question);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        a.setAuthor(author);
        this.answerRepository.save(a);
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isEmpty()) {
            throw new RuntimeException("answer not found");
        } else {
            return answer.get();
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
