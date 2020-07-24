package com.whoami.controller;

import com.whoami.model.WhoAmIResponse;
import com.whoami.model.WhoAmIRequest;
import com.whoami.service.GuessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WhoAmIController {

    @Autowired
    GuessInfoService guessInfoService;

    @PostMapping("/whoami")
    @ResponseStatus(HttpStatus.OK)
    public WhoAmIResponse whoAmI(@RequestBody WhoAmIRequest whoAmIRequest) {
        return guessInfoService.getGuesses(whoAmIRequest.getFirst_name(), whoAmIRequest.getCountry_code());
    }

}
