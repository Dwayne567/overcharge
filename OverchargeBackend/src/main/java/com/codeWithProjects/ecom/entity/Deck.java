package com.codeWithProjects.ecom.entity;

import com.codeWithProjects.ecom.dto.customer.CardDto;
import com.codeWithProjects.ecom.dto.customer.DeckDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "decks")
public class Deck {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

	@Column(name = "title")
	private String title;

	@Transient
	private List<CardDto> cards = new ArrayList<>();

	public DeckDto getDto() {
		DeckDto deckDto = new DeckDto();
		deckDto.setId(id);
		deckDto.setUserId(user.getId());
		deckDto.setTitle(title);
		deckDto.setCards(cards);
		return deckDto;
	}
}
