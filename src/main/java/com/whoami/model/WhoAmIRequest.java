package com.whoami.model;

import lombok.Data;

@Data
public class WhoAmIRequest {
    private String first_name;
    private String country_code;
}
