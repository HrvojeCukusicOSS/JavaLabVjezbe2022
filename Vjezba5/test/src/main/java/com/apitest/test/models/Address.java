package com.apitest.test.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="addresses")
public class Address {
    private @Id
    @GeneratedValue Long id;

    private String number;

    private String city;

    @OneToMany(mappedBy="address")
    @JsonIgnore
    private Set<User> users;
}
