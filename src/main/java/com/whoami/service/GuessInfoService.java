package com.whoami.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whoami.adapter.HttpClientWrapper;
import com.whoami.model.AgeGuess;
import com.whoami.model.WhoAmIResponse;
import com.whoami.model.GenderGuess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GuessInfoService {

    @Value("${agify.url}")
    private String agifyUrl;
    @Value("${genderize.url}")
    private String genderizeUrl;
    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<WhoAmIResponse> getGuesses(String name, String countryCode) {
        if (name == null || countryCode == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AgeGuess ageGuess = null;
        GenderGuess genderGuess = null;
        String ageResponse = new HttpClientWrapper().get(String.format(agifyUrl, name, countryCode));
        String genderResponse = new HttpClientWrapper().get(String.format(genderizeUrl, name, countryCode));
        try {
            ageGuess = objectMapper.readValue(ageResponse, AgeGuess.class);
            genderGuess = objectMapper.readValue(genderResponse, GenderGuess.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(
                WhoAmIResponse.builder()
                    .first_name(name)
                    .country_code(countryCode)
                    .age(ageGuess.getAge())
                    .gender(genderGuess.getGender())
                    .build());
    }

}
