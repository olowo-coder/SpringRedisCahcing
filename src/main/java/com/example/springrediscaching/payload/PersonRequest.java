package com.example.springrediscaching.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonRequest implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
