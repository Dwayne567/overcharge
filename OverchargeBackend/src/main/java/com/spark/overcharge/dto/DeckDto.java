package com.spark.overcharge.dto;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DeckDto {
    private Long id;
    private Long userId;
    private String title;
    private List<CardDto> cards = new ArrayList<>();
}
