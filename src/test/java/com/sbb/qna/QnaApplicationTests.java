package com.sbb.qna;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class QnaApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	void test02() {
		List<Question> questionList = this.questionRepository.findAll();
		assertEquals(1, questionList.size());
	}

	@Test
	void test03() {
		Optional<Question> optionalQuestion = this.questionRepository.findById(1);
		if (optionalQuestion.isPresent()) {
			Question question = optionalQuestion.get();
			assertEquals(1, question.getId());
		}

	}

	@Test
	void test04() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	@Test
	void test05() {
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "");
			assertEquals(1, q.getId());
	}
	@Test
	void test06() {
		List<Question> questionList = this.questionRepository.findBySubjectAndLike("sbb%");
		assertEquals(1, questionList.size());
	}
}
