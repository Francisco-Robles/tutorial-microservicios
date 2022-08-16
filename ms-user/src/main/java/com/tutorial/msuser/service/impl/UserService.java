package com.tutorial.msuser.service.impl;

import com.tutorial.msuser.entity.User;
import com.tutorial.msuser.feignclients.BikeFeignClient;
import com.tutorial.msuser.feignclients.CarFeignClient;
import com.tutorial.msuser.model.Bike;
import com.tutorial.msuser.model.Car;
import com.tutorial.msuser.repository.UserRepository;
import com.tutorial.msuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById (Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser (User user){
        User newUser = userRepository.save(user);
        return newUser;

    }

    public List<Car> getCars (Long userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byUser/" + userId, List.class);
        return cars;
    }

    public List<Bike> getBikes (Long userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byUser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar (Long userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }

    public Bike saveBike(Long userId, Bike bike){

        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;

    }

    public Map<String, Object> getUserAndVehicles (Long userId){

        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            result.put("Mensaje", "no existe el usuario");
            return result;
        }else{
            result.put("User", user);
            List<Car> cars = carFeignClient.getCars(userId);
            if (cars.isEmpty()){
                result.put("Cars","ese user no posee autos");
            }else{
                result.put("Cars",cars);
            }

            List<Bike> bikes = bikeFeignClient.getBikes(userId);
            if (bikes.isEmpty()){
                result.put("Bikes", "ese user no tiene motos");
            }else{
                result.put("Bikes", bikes);
            }
        }

        return result;

    }


}
