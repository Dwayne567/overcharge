package com.spark.overcharge.services;

import java.util.List;

import com.spark.overcharge.dto.CardDto;

public interface CardService {
	public List<CardDto> getAllCardsByUserId(Long userId);
}