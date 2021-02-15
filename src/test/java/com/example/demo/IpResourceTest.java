package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.Exception;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class IpResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnErrorAboutNotPresentIp() throws Exception {
        this.mockMvc.perform(get("/north-countries"))//
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(status().reason(containsString("Required List parameter 'ip' is not present")));
    }

    @Test
    void shouldReturnErrorAboutEmptyList() throws Exception {
        this.mockMvc.perform(get("/north-countries?ip="))//
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Input movie list cannot be empty.")));
    }

    @Test
    void shouldReturnErrorAboutTooLongList() throws Exception {
        this.mockMvc.perform(get("/north-countries?ip=" +
                "ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0&ip=199.0.0.0"))//
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("The input list cannot contain more than 5 addresses.")));
    }

    @Test
    void shouldReturnList() throws Exception {
        this.mockMvc.perform(get("/north-countries?" +
                "ip=8.8.8.8"))//
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"northcountries\":[\"United States\"]}")));
    }


    @Test
    void shouldReturnListWithNotDuplicate() throws Exception {
        this.mockMvc.perform(get("/north-countries?" +
                "ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"))//
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"northcountries\":[\"Colombia\",\"Japan\",\"United " +
                        "States\"]}")));
    }

    @Test
    void shouldReturnEmptyListBecauseWrongIp() throws Exception {
        this.mockMvc.perform(get("/north-countries?" +
                "ip=0.0.0.0"))//
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"northcountries\":[]}")));
    }

    @Test
    void shouldReturnEmptyListBecauseNotNorthCountry() throws Exception {
        this.mockMvc.perform(get("/north-countries?" +
                "ip=1.1.1.1"))//
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"northcountries\":[]}")));
    }
}