package com.carrental.controller;

import com.carrental.model.dto.CarDto;
import com.carrental.payload.response.ResponseMessage;
import com.carrental.model.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Service
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    CarService carService;

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CarDto carDto) {
        if (carDto.getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required!"), HttpStatus.OK);
        }
        carService.save(carDto);
        return new ResponseEntity<>(new ResponseMessage("Create car success!"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> showListCar() {
        Iterable<CarDto> cars = carService.findAll();
        if (cars == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CarDto carDto) {
        Optional<CarDto> carDto1 = carService.findById(id);

        if (!carDto1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (carDto1.get().getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required"), HttpStatus.OK);
        }
        carDto1.get().setName(carDto.getName());
        carService.save(carDto1.get());
        return new ResponseEntity<>(new ResponseMessage("Update success!"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<CarDto> carDto = carService.findById(id);

        if (!carDto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carService.remove(carDto.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete success!"), HttpStatus.OK);
    }
}
