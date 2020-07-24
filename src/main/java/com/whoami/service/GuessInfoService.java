package com.whoami.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whoami.adapter.HttpClientWrapper;
import com.whoami.model.AgeGuess;
import com.whoami.model.FullGuess;
import com.whoami.model.GenderGuess;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.io.IOException;

@Service
public class GuessInfoService {

    @Value("${agify.url}")
    private String agifyUrl;
    @Value("${genderize.url}")
    private String genderizeUrl;
    private ObjectMapper objectMapper;

    public FullGuess getGuesses(String name, String countryCode) {
        AgeGuess ageGuess = null;
        GenderGuess genderGuess = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String ageResponse = new HttpClientWrapper().get(String.format(agifyUrl, name, countryCode));
        String genderResponse = new HttpClientWrapper().get(String.format(genderizeUrl, name, countryCode));
        try {
            ageGuess = objectMapper.readValue(ageResponse, AgeGuess.class);
            genderGuess = objectMapper.readValue(genderResponse, GenderGuess.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FullGuess.builder()
                .full_name(name)
                .country_code(countryCode)
                .age(ageGuess.getAge())
                .gender(genderGuess.getGender())
                .build();
    }

}
