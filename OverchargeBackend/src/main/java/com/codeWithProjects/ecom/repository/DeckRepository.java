package com.codeWithProjects.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeWithProjects.ecom.entity.Deck;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
	List<Deck> findByUser_Id(Long userId);
}
