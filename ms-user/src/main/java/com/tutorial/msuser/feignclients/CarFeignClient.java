package com.tutorial.msuser.feignclients;

import com.tutorial.msuser.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", path = "/car")
public interface CarFeignClient {

    @PostMapping()
    Car save (@RequestBody Car car);

    @GetMapping("/byUser/{userId}")
    List<Car> getCars (@PathVariable("userId") Long userId);

}
