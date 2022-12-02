package com.gyojincompany.board.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.gyojincompany.board.entity.QuestionBoard;
import com.gyojincompany.board.repository.QBoardRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestQBoard {
	
	@Autowired
	QBoardRepository qBoardRepository;
	
	@Test
	@DisplayName("저장 테스트")
	public void createQuestion() {
		QuestionBoard qBoard = new QuestionBoard();
		
		qBoard.setSubject("안녕");
		qBoard.setContent("안녕");
		
		qBoardRepository.save(qBoard);
	}
	
	@Test
	@DisplayName("조회 테스트1")
	public void searchQuestion1() {
		
		List<QuestionBoard> qAll = qBoardRepository.findAll();
		assertEquals(4, qAll.size());//실제 모든 글의 개수와 기대한 값인 2개가 일치하는지 여부 확인
		
		QuestionBoard q1 = qAll.get(1);
		assertEquals("질문2개 있습니다.", q1.getSubject()); 
		
	}
	
	@Test
	@DisplayName("조회 테스트2")
	public void searchQuestion2() {
		
		Optional<QuestionBoard> qBoard = qBoardRepository.findById(2);		
		
		if(qBoard.isPresent()) {		
			QuestionBoard q1 = qBoard.get();
			assertEquals("질문2개 있습니다.", q1.getSubject());
		}		
	}
	
	@Test
	@DisplayName("조회 테스트3")
	public void searchQuestion3() {
		
		List<QuestionBoard> qBoards = qBoardRepository.findBySubject("질문");	
					
		QuestionBoard q1 = qBoards.get(0);
		assertEquals(5, q1.getId());
				
	}
	
	
	
}
