package com.example.Backend.controller;

import com.example.Backend.dto.CountryDTO;
import com.example.Backend.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryDTO> getCountries(@RequestParam(required = false) String search) {
        // Fetch Countries [cite: 12]
        List<CountryDTO> allCountries = countryService.fetchAllCountries();

        // Search Countries  and Return filtered results [cite: 23]
        if (search != null && !search.trim().isEmpty()) {
            return allCountries.stream()
                    .filter(c -> c.name().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return allCountries;
    }
}
