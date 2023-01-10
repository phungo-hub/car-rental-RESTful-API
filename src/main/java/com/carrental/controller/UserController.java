package com.carrental.controller;

import com.carrental.model.dto.UserDto;
import com.carrental.payload.response.ResponseMessage;
import com.carrental.model.service.impl.RoleServiceImpl;
import com.carrental.model.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;

    @GetMapping
    public ResponseEntity<?> showUsers() {
        Iterable<UserDto> users = userService.findAll();
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(userDto.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        userService.save(userDto);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto carDto) {
        Optional<UserDto> userDto1 = userService.findById(id);

        if (!userDto1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userDto1.get().getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required"), HttpStatus.OK);
        }
        userDto1.get().setName(carDto.getName());
        userService.save(userDto1.get());
        return new ResponseEntity<>(new ResponseMessage("Update success!"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<UserDto> userDto = userService.findById(id);

        if (!userDto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(userDto.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete success!"), HttpStatus.OK);
    }
}
