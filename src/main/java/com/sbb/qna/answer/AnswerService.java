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

    public Answer create(Question question, String content, SiteUser author) {

        Answer a = new Answer();
        a.setQuestion(question);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        a.setAuthor(author);
        this.answerRepository.save(a);
        return a;
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new RuntimeException("answer을 찾을수 없습니다");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
