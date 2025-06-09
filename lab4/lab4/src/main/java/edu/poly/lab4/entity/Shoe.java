package edu.poly.lab4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shoes")
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String brand;

    @Column(length = 1000)
    private String description;
}