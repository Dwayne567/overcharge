package com.spark.millhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spark.millhouse.model.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

}
