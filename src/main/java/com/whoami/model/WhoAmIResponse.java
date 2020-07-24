package com.whoami.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WhoAmIResponse {
    private String first_name;
    private String country_code;
    private int age;
    private String gender;
}
