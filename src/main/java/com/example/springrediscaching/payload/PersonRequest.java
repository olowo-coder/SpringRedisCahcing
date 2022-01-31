package com.example.springrediscaching.payload;

import lombok.Data;

@Data
public class PersonRequest {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
