package com.spark.overcharge.exceptions;

public class DeckNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DeckNotFoundException(Long id) {
        super("Deck with id " + id + " not found");
    }
}
