package com.tutorial.mscar.controller;

import com.tutorial.mscar.entity.Car;
import com.tutorial.mscar.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getAll();
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(cars);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById (@PathVariable("id") Long id){
        Car Car = carService.getCarById(id);
        if (Car == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(Car);
    }

    @PostMapping()
    public ResponseEntity<Car> save (@RequestBody Car car){
        Car CarNew = carService.saveCar(car);
        return ResponseEntity.ok(CarNew);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") Long userId){
        List<Car> cars = carService.findByUserId(userId);
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(cars);
        }
    }

}
