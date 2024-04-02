package com.spark.overcharge.exceptions;

public class CardNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardNotFoundException(Long id) {
        super("Card with id " + id + " not found");
    }
}
