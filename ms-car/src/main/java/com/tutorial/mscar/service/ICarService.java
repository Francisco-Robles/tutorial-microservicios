package com.tutorial.mscar.service;

import com.tutorial.mscar.entity.Car;
import java.util.List;

public interface ICarService {

    Car saveCar (Car car);

    Car getCarById(Long id);

    List<Car> getAll();

    List<Car> findByUserId(Long userId);

}
