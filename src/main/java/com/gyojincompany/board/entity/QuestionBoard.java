package com.gyojincompany.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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
}
