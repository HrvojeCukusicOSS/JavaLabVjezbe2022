package com.apitest.test.models;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Device {
    private @Id
    @GeneratedValue Long id;

    private int value;

    private String measurement_unit;

    @ManyToOne
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
