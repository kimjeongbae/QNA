package com.sbb.qna.answer;

import com.sbb.qna.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
