package com.carrental.model.service.impl;

import com.carrental.model.dto.CarDto;
import com.carrental.model.entity.Car;
import com.carrental.model.repository.CarRepository;
import com.carrental.model.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@ComponentScan(basePackageClasses = ModelMapper.class)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarDto> getCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getCarsName(String fullName) {
        String likeFullName = "%" + fullName + "%";
        List<Car> cars = carRepository.findByCarName(likeFullName);
        return cars.stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<CarDto> findAll() {
        Iterable<Car> entities = carRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        carRepository.save(car);
    }

    @Override
    public void remove(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Optional<CarDto> findById(Long id) {
        Car entity = carRepository.findById(id).orElse(null);
        return Optional.ofNullable(modelMapper.map(entity, CarDto.class));
    }
}

