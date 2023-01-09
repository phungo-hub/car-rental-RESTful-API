package com.carrental.model.dto;

import com.carrental.model.entity.Category;
import com.carrental.model.entity.Owner;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CarDto {
    private Long id;
    private String name;
    private double price;
    private String model;
    private int year;
    private String img;
    private Owner owner;
    private Category category;
}
