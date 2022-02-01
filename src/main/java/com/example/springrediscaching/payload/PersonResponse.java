package com.example.springrediscaching.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PersonResponse implements Serializable {
    private Long id;
    private String firstName;
    private String email;
    private String status;

    public PersonResponse(String firstName, String email, String status) {
        this.firstName = firstName;
        this.email = email;
        this.status = status;
    }
}
