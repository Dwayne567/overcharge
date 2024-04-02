package com.spark.overcharge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.overcharge.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	Optional<List<Card>> findByDeckId(Long deckId);
	Optional<List<Card>> findByUserId(Long userId);
}
