package com.tutorial.msbike.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bike {

    @Id
    @GeneratedValue
    private Long id;

    private String brand;

    private String model;

    private Long userId;

}
