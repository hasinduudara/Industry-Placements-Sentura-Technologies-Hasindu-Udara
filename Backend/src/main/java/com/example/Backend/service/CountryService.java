package com.example.Backend.service;

import com.example.Backend.dto.CountryDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://restcountries.com/v3.1/all";

    @Cacheable("countries")
    @SuppressWarnings("unchecked")
    public List<CountryDTO> fetchAllCountries() {
        List<Map<String, Object>> response = restTemplate.getForObject(API_URL, List.class);

        if (response == null) return List.of();

        return response.stream().map(data -> {
            Map<String, Object> nameMap = (Map<String, Object>) data.get("name");
            String name = nameMap != null ? (String) nameMap.get("common") : "Unknown";

            List<String> capitals = (List<String>) data.get("capital");
            String capital = (capitals != null && !capitals.isEmpty()) ? capitals.getFirst() : "N/A";

            String region = (String) data.get("region");
            Number pop = (Number) data.get("population");
            long population = pop != null ? pop.longValue() : 0;

            Map<String, String> flags = (Map<String, String>) data.get("flags");
            String flag = flags != null ? flags.get("png") : "";

            return new CountryDTO(name, capital, region, population, flag);
        }).collect(Collectors.toList());
    }

    @CacheEvict(value = "countries", allEntries = true)
    @Scheduled(fixedRate = 600000)
    public void refreshCache() {
        System.out.println("Cache Cleared!");
    }
}
