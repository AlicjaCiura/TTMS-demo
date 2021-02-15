package com.example.demo;

import com.example.demo.validations.ValidationMaxSize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Value("${ip.api.ur}")
    private static final String URL_IP_API = "http://ip-api.com/json/";

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/north-countries")
    public CountriesDto getCountries(
            @RequestParam @Valid @NotEmpty(message = "Input movie list cannot be empty.") @ValidationMaxSize.MaxSizeConstraint List<String> ip) {
        logger.info("/north-countries: {}", ip);
        List<String> countries = ip.stream()//
                .map(i -> restTemplate.getForObject(URL_IP_API + i, CountryData.class))//
                .filter(Objects::nonNull)//
                .filter(CountryData::isNorthCountry)//
                .map(CountryData::getCountry).distinct()//
                .sorted()//
                .collect(Collectors.toList());

        return CountriesDto.of(countries);
    }

    @AllArgsConstructor(staticName = "of")
    public static class CountriesDto {
        @Getter
        private final List<String> northcountries;
    }
}
