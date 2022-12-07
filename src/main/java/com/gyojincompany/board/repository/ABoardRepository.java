package com.gyojincompany.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gyojincompany.board.entity.AnswerBoard;

public interface ABoardRepository extends JpaRepository<AnswerBoard, Integer>{	
	
}
