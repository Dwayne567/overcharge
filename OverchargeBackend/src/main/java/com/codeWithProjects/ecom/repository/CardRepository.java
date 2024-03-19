package com.codeWithProjects.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeWithProjects.ecom.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	Optional<List<Card>> findByDeckId(Long deckId);
	List<Card> findCardsByDeckId(Long deckId);
}
