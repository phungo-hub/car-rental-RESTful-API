package com.carrental.model.service;

import com.carrental.model.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService extends GeneralService<CarDto> {
    List<CarDto> getCars();
    List<CarDto> getCarsName(String fullName);
    Iterable<CarDto> findAll();
    void save(CarDto carDto);
    void remove(Long id);
    Optional<CarDto> findById(Long id);

}
