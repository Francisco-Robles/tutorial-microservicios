package com.tutorial.msbike.service;

import com.tutorial.msbike.entity.Bike;

import java.util.List;

public interface IBikeService {

    Bike saveBike (Bike bike);

    Bike getBikeById(Long id);

    List<Bike> getAll();

    List<Bike> findByUserId(Long userId);

}
