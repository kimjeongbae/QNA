package com.sbb.qna;

import com.sbb.qna.answer.Answer;
import com.sbb.qna.answer.AnswerRepository;
import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class QnaApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void test01() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }

    @Test
    void test02() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("testSub", q.getSubject());
        }
    }

    @Test
    void test03() {
        Question q = this.questionRepository.findBySubject("testSub");
        assertEquals(1, q.getId());
    }


    @Test
    void test04() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    void test05() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void test06() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void test07() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    @Test
    void test08() {
        Optional<Question> oq = this.questionRepository.findById(1);

        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a1 = new Answer();
        a1.setContent("답변입니다.");
        a1.setCreatedDate(LocalDateTime.now());
        this.answerRepository.save(a1);
    }

    @Test
    void test09() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals(1, q.getSubject());
            assertEquals(2, q.getSubject());
        }


    }
}

