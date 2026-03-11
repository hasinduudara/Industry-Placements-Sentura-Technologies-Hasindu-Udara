package com.example.Backend.dto;

public record CountryDTO(
        String name,
        String capital,
        String region,
        long population,
        String flag
) {}
