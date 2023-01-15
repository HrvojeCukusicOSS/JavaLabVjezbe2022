package com.apitest.test.models;

import jakarta.persistence.*;

import lombok.Data;


@Data
@Entity
public class Address {
    private @Id
    @GeneratedValue Long id;

    private String number;

    private String city;
}
