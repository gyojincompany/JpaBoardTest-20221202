package com.gyojincompany.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gyojincompany.board.entity.QuestionBoard;

public interface QBoardRepository extends JpaRepository<QuestionBoard, Integer>{
	
	public List<QuestionBoard> findBySubject(String subject);
	
}
