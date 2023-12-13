package com.spark.millhouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
    private long id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer", length = 1000)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    @JsonBackReference
    private Deck deck;
    
	// The @ManyToOne annotation is used to establish a many-to-one relationship between two entities.
	// It indicates that the annotated field represents a reference to another entity (in this case, a Deck entity),
	// and multiple instances of the current entity (Card) can be associated with a single instance of the referenced entity.

    // The @JoinColumn annotation is used to specify the details of the foreign key column that links the two entities in a database table.
    // In this case, name = "deck_id" specifies that the foreign key column in the current entity's table 
    // will be named "deck_id," and it will be used to reference the Deck entity.
    // By default, if you don't specify the name attribute, JPA will use the name of the referenced entity's primary key column.
    // In this case, it's explicitly set to "deck_id."
    
    // So, the code you've provided indicates that the current entity has a many-to-one relationship with the Deck entity,
    // and this relationship is maintained in the database through a foreign key column named "deck_id." 
    // This foreign key column links instances of the current entity to instances of the Deck entity in a relational database.
    
    // This is a common pattern in JPA for modeling relationships between entities in a database,
    // and it allows for more complex data structures and relationships to be represented in your Java applications
    // while maintaining the integrity of the underlying database.
    
	/* -- -- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- ---  */
	/* -- -- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- -- ---  */

    // Constructors, getters, and setters
    public Card() {
    }

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
    
    public Card(String question, String answer, Deck deck) {
        this.question = question;
        this.answer = answer;
        this.deck = deck;
    }

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
    
}

