package com.carrental.controller;

import com.carrental.model.dto.CategoryDto;
import com.carrental.payload.response.ResponseMessage;
import com.carrental.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        if (categoryDto.getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required!"), HttpStatus.OK);
        }
        categoryService.save(categoryDto);
        return new ResponseEntity<>(new ResponseMessage("Create category success!"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> showListCategory() {
        Iterable<CategoryDto> categories = categoryService.findAll();
        if (categories == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Optional<CategoryDto> categoryDto1 = categoryService.findById(id);

        if (!categoryDto1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (categoryDto1.get().getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required"), HttpStatus.OK);
        }
        categoryDto1.get().setName(categoryDto.getName());
        categoryService.save(categoryDto1.get());
        return new ResponseEntity<>(new ResponseMessage("Update success!"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<CategoryDto> categoryDto = categoryService.findById(id);

        if (!categoryDto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(categoryDto.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete success!"), HttpStatus.OK);
    }
}
