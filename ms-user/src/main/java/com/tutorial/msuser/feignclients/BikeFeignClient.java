package com.tutorial.msuser.feignclients;

import com.tutorial.msuser.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service", path = "/bike")
public interface BikeFeignClient {

    @PostMapping()
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byUser/{userId}")
    List<Bike> getBikes (@PathVariable("userId") Long userId);

}
