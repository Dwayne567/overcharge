package com.spark.overcharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spark.overcharge.entity.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
	List<Deck> findByUserId(Long userId);
}