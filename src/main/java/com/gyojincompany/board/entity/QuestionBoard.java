package com.gyojincompany.board.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name = "qboard00_seq_gengerator", 
	sequenceName = "qboard00_seq", 
	allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(length = 1000)//255자 제한 해제
	private String content;
	
	@CreationTimestamp	
	@Column(updatable = false)
	private LocalDateTime createTime;
	
	@OneToMany(mappedBy = "questionBoard", cascade = CascadeType.REMOVE)
	//질문이 삭제되면 그 질문에 달린 답변들도 모두 삭제
//	private AnswerBoard answerBoard;
	private List<AnswerBoard> answerList;
	
}
