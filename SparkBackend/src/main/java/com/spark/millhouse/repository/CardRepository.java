package com.spark.millhouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spark.millhouse.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

	List<Card> findByDeckId(Long deckId);
}
