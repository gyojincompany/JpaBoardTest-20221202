package com.gyojincompany.board.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name = "aboard00_seq_gengerator", 
	sequenceName = "aboard00_seq", 
	allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 1000)
	private String content;
	
	@CreationTimestamp	
	@Column(updatable = false)
	private LocalDateTime createTime;
	
	@ManyToOne
	private QuestionBoard questionBoard;
	
}
