package com.company.internship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = "vin"),
        @UniqueConstraint(columnNames = "licensePlate")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;

    private Integer year;

    @Column(length = 17, unique = true)
    private String vin;

    @Column(unique = true)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;
}