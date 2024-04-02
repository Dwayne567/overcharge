package com.spark.overcharge.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spark.overcharge.dto.CardDto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question")
    private String question;

    @Lob
    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deck_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deck deck;
    
    public CardDto getDto(){
    	CardDto cardDto = new CardDto();
    	cardDto.setId(id);
    	cardDto.setUserId(user.getId());
        cardDto.setDeckId(deck.getId());
        cardDto.setQuestion(question);
        cardDto.setAnswer(answer);
        return cardDto;
    }
}

