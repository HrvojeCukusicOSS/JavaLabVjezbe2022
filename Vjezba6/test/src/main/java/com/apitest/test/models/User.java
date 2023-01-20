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
@Table(name="users")
public class User {

    private @Id
    @GeneratedValue Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="address_id")
    @JsonIgnore
    private Address address;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private Set<Device> devices;
}
