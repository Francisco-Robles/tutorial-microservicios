package com.tutorial.msbike.controller;

import com.tutorial.msbike.entity.Bike;
import com.tutorial.msbike.service.IBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private IBikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> Bikes = bikeService.getAll();
        if (Bikes.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(Bikes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById (@PathVariable("id") Long id){
        Bike Bike = bikeService.getBikeById(id);
        if (Bike == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(Bike);
    }

    @PostMapping()
    public ResponseEntity<Bike> save (@RequestBody Bike Bike){
        Bike BikeNew = bikeService.saveBike(Bike);
        return ResponseEntity.ok(BikeNew);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") Long userId){
        List<Bike> Bikes = bikeService.findByUserId(userId);
        if(Bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(Bikes);
        }
    }

}
