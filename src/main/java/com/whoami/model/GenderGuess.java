package com.whoami.model;

import lombok.Data;

@Data
public class GenderGuess {
    private String name;
    private String gender;
    private double probability;
    private double count;
    private String country_id;
}
