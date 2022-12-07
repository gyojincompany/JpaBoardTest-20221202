package com.gyojincompany.board.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.gyojincompany.board.entity.AnswerBoard;
import com.gyojincompany.board.entity.QuestionBoard;
import com.gyojincompany.board.repository.ABoardRepository;
import com.gyojincompany.board.repository.QBoardRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestABoard {

	@Autowired
	private QBoardRepository qBoardRepository;
	
	@Autowired
	private ABoardRepository aBoardRepository;
	
	@Test
	@DisplayName("답변 저장 테스트")
	public void AnswerCreatTest() {
		
		Optional<QuestionBoard> oQboard = qBoardRepository.findById(7);
		assertTrue(oQboard.isPresent());//7번 질문글이 존재하는지 테스트
		
		QuestionBoard qBoard = oQboard.get();
		
		AnswerBoard aBoard = new AnswerBoard();
		
		aBoard.setContent("7번글 답변입니다.");
		aBoard.setQuestionBoard(qBoard);
		
		aBoardRepository.save(aBoard);
	}
	
	@Test
	@DisplayName("답변 조회 테스트")
	public void answerSearch() {
		
		Optional<AnswerBoard> oAboard = aBoardRepository.findById(20);
		assertTrue(oAboard.isPresent());
		AnswerBoard aBoard = oAboard.get();
		
		assertEquals(7, aBoard.getQuestionBoard().getId());
		//질문글의 아이디를 가져와서 확인
	}
	
	@Transactional
	@Test
	@DisplayName("답변/질문 조회 테스트")
	public void answerQuestionSearch() {
		Optional<QuestionBoard> oQboard = qBoardRepository.findById(7);
		assertTrue(oQboard.isPresent());//7번 질문글이 존재하는지 테스트
		
		QuestionBoard qBoard = oQboard.get();//7번 질문글 가져옴
		
		List<AnswerBoard> aBoards = qBoard.getAnswerList();//답변글 리스트 가져오기
		
		assertEquals(10, aBoards.size());
		//질번 7번에 달린 답변 글의 개수가 총 6개인지 확인
	}
	
	
}
