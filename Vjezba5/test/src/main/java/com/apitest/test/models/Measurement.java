package com.apitest.test.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int value;

    private String measurement_unit;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "device_id", updatable = false)
    @JsonIgnore
    private Device device;
}
