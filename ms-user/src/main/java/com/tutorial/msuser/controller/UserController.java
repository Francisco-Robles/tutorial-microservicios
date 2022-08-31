package com.tutorial.msuser.controller;

import com.tutorial.msuser.entity.User;
import com.tutorial.msuser.model.Bike;
import com.tutorial.msuser.model.Car;
import com.tutorial.msuser.service.IUserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById (@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        if (user == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save (@RequestBody User user){
        User userNew = userService.saveUser(user);
        return ResponseEntity.ok(userNew);
    }
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackGetBikes")
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") Long userId){
        User user = userService.getUserById(userId);
        if (user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car){

        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }else{
            Car carNew = userService.saveCar(userId, car);
            return ResponseEntity.ok(carNew);
        }

    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveBike")
    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike){

        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }else{
            Bike bikeNew = userService.saveBike(userId, bike);
            return ResponseEntity.ok(bikeNew);
        }

    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles (@PathVariable("userId") Long userId){
        Map<String,Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

    private ResponseEntity <List<Car>> fallBackGetCars (@PathVariable("userId") Long userId, Exception e){
        return new ResponseEntity("El usuario " + userId + " tiene los autos en el taller.", HttpStatus.OK);
    }

    private ResponseEntity<Car> fallBackSaveCar (@PathVariable("userId") Long userId, @RequestBody Car car, Exception e){
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para autos.", HttpStatus.OK);
    }

    private ResponseEntity <List<Bike>> fallBackGetBikes (@PathVariable("userId") Long userId){
        return new ResponseEntity("El usuario " + userId + " tiene las motos en el taller.", HttpStatus.OK);
    }

    private ResponseEntity<Bike> fallBackSaveBike (@PathVariable("userId") Long userId, @RequestBody Bike biker, Exception e){
        return new ResponseEntity("El usuario " + userId + " no tiene dinero para motos.", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> fallBackGetAll (@PathVariable("userId") Long userId, Exception e){
        return new ResponseEntity("El usuario " + userId + " tiene los autos y las motos en el taller.", HttpStatus.OK);
    }

}
