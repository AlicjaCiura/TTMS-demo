package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Getter;

class CountryData implements Serializable {
    @Getter
    private final String status;
    @Getter
    private final String country;
    @Getter
    private final String city;
    @Getter
    private final float latitude;

    @JsonCreator
    public CountryData(@JsonProperty("status") String status, @JsonProperty("country") String country,
            @JsonProperty("city") String city, @JsonProperty("lat") float latitude) {
        this.status = status;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return this.country + ", " + latitude;
    }

    public boolean isNorthCountry() {
        return latitude > 0;
    }
}
