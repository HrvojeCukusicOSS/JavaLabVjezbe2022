package com.apitest.test.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Data
@Entity
public class User {

    private @Id
    @GeneratedValue Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="address_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;
}
