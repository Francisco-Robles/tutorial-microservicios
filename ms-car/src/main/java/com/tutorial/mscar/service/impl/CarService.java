package com.tutorial.mscar.service.impl;

import com.tutorial.mscar.entity.Car;
import com.tutorial.mscar.repository.CarRepository;
import com.tutorial.mscar.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements ICarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById (Long id){
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar (Car car){
        Car newCar = carRepository.save(car);
        return newCar;

    }

    public List<Car> findByUserId(Long userId){
        return carRepository.findByUserId(userId);
    }

}
