package com.example.springrediscaching.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonResponse {

    private String firstName;
    private String email;
    private String status;

    public PersonResponse(String firstName, String email, String status) {
        this.firstName = firstName;
        this.email = email;
        this.status = status;
    }
}
