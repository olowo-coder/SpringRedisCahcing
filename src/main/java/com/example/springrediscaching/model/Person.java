package com.example.springrediscaching.model;

import lombok.Data;


@Data
public class Person{

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
