package com.apitest.test.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="devices")
public class Device {
    private @Id
    @GeneratedValue Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private Set<Measurement> values;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
}
