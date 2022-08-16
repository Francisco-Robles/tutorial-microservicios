package com.tutorial.msuser.service;

import com.tutorial.msuser.entity.User;
import com.tutorial.msuser.model.Bike;
import com.tutorial.msuser.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IUserService {

    User saveUser (User user);

    User getUserById(Long id);

    List<User> getAll();

    List<Car> getCars (Long userId);

    List<Bike> getBikes(Long userId);

    Car saveCar (Long userId, Car car);

    Bike saveBike (Long userId, Bike bike);

    Map<String, Object> getUserAndVehicles (Long userId);

}
