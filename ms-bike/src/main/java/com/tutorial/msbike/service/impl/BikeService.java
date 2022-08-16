package com.tutorial.msbike.service.impl;

import com.tutorial.msbike.entity.Bike;
import com.tutorial.msbike.repository.BikeRepository;
import com.tutorial.msbike.service.IBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService implements IBikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById (Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike saveBike (Bike Bike){
        Bike newBike = bikeRepository.save(Bike);
        return newBike;

    }

    public List<Bike> findByUserId(Long userId){
        return bikeRepository.findByUserId(userId);
    }

}
