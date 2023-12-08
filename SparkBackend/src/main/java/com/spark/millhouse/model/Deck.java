package com.spark.millhouse.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="decks")
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deck_id")
    private Long id;
    
	@Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Card> cards;
    
    // @OneToMany: This annotation is used to declare a one-to-many relationship.
    // It indicates that one instance of the Deck entity can be associated with multiple instances of the Card entity.

    // mappedBy = "deck": This part of the annotation specifies that the relationship is mapped by the "deck" attribute in the Card entity.
    // In other words, the "deck" field in the Card entity holds the foreign key that links each card to its associated deck.

    // cascade = CascadeType.ALL: The cascade attribute specifies how the entity should propagate changes to its related entities. 
    // In this case, you've set it to CascadeType.ALL, which means that any operations (such as persist, merge, remove, etc.) performed on a Deck entity
    // will be cascaded to its associated Card entities. 
    // For example, if you remove a Deck, all its associated Card entities will also be removed.

    // With this mapping, when you retrieve a Deck entity from the database, you can access its associated Card entities through the cards property. 
    // And any changes made to the Deck (e.g., adding or removing cards) will be cascaded to the related Card entities.
    
	/* -- -- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- ---  */
	/* -- -- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- ---  */
    
    // Constructors, getters, and setters
    public Deck() {
    }
    
	public Deck(String title, List<Card> cards) {
		this.title = title;
		this.cards = cards;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}


