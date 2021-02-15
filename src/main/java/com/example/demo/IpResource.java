package com.example.demo;

import com.example.demo.validations.ValidationMaxSize;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@Validated
public class IpResource {

    private static final String IP_RESOURCE_URL = "http://ip-api.com/json/";

    @GetMapping("/north-countries")
    public CountriesDto getCountries(
            @RequestParam @Valid @NotEmpty(message = "Input movie list cannot be empty.") @ValidationMaxSize.MaxSizeConstraint List<String> ip) {

        RestTemplate restTemplate = new RestTemplate();
        List<String> countries = ip.stream()//
                .map(i -> restTemplate.getForObject(IP_RESOURCE_URL + i, CountryData.class))//
                .filter(Objects::nonNull)//
                .filter(CountryData::isNorthCountry)//
                .map(CountryData::getCountry).distinct()//
                .sorted()//
                .collect(Collectors.toList());

        return CountriesDto.of(countries);
    }

    @AllArgsConstructor(staticName = "of")
    private static class CountriesDto {
        @Getter
        private final List<String> northcountries;
    }
}
