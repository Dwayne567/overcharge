package com.spark.overcharge.dto;
import lombok.Data;

@Data
public class CardDto {
    private Long id;
    private Long userId;
    private Long deckId;
    private String question;
    private String answer;
}
