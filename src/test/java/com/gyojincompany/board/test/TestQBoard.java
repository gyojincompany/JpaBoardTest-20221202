package com.gyojincompany.board.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		
		qBoard.setSubject("안녕1");
		qBoard.setContent("안녕1");
		
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
	
	@Test
	@DisplayName("조회 테스트4")
	public void searchQuestion4() {
		
		List<QuestionBoard> qBoards = qBoardRepository.findBySubjectAndContent("안녕1", "안녕1");
					
		QuestionBoard q1 = qBoards.get(0);
//		System.out.println(qBoards.getId());
		assertEquals(6, q1.getId());
				
	}
	
	@Test
	@DisplayName("조회 테스트5")
	public void searchQuestion5() {
		
		List<QuestionBoard> qBoards = qBoardRepository.findBySubjectLike("%질문%");					
			
		assertEquals(6, qBoards.size());
				
	}
	
	@Test
	@DisplayName("수정 테스트")
	public void modifyTest() {
		Optional<QuestionBoard> oResult = qBoardRepository.findById(2);//아이디가 2번인 글 조회
		
		QuestionBoard qResult = oResult.get();
		qResult.setSubject("저는 2번글입니다");
		QuestionBoard qr = qBoardRepository.save(qResult);
		assertEquals("저는 2번글입니다", qr.getSubject());

	}
	
	@Test
	@DisplayName("삭제 테스트")
	public void deleteTest() {
		
//		List<QuestionBoard> qAll = qBoardRepository.findAll();
//		int qAllSize1 = qAll.size();//모든 글의 개수
		
		int qAllSize1 = (int) qBoardRepository.count();//모든 데이터 개수 조회
		
		Optional<QuestionBoard> oResult = qBoardRepository.findById(5);//아이디가 3번인 글 조회
		
		assertTrue(oResult.isPresent());
		
		QuestionBoard qResult = oResult.get();//3번 글 가져오기 완료		
		
		qBoardRepository.delete(qResult);
		
//		qAll = qBoardRepository.findAll();
//		int qAllSize2 = qAll.size();//모든 글의 개수
		int qAllSize2 = (int) qBoardRepository.count();
		assertEquals(qAllSize2, qAllSize1-1);

	}
	
}
