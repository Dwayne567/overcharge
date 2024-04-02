package com.spark.overcharge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spark.overcharge.dto.DeckDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	@JsonManagedReference
	@OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
	private List<Card> cards = new ArrayList<>();

	public DeckDto getDto() {
		DeckDto deckDto = new DeckDto();
		deckDto.setId(id);
		deckDto.setUserId(user.getId());
		deckDto.setTitle(title);
	    deckDto.setCards(cards.stream().map(Card::getDto).collect(Collectors.toList()));
		return deckDto;
	}
}