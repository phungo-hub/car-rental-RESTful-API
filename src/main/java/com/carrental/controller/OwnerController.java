package com.carrental.controller;

import com.carrental.model.dto.OwnerDto;
import com.carrental.model.service.SecurityService;
import com.carrental.payload.response.ResponseMessage;
import com.carrental.model.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @Autowired
    SecurityService securityService;

    @PostMapping
    public ResponseEntity<?> createOwner(@RequestBody OwnerDto ownerDto,
                                         @RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        if (ownerDto.getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required!"), HttpStatus.OK);
        }
        ownerService.save(ownerDto);
        return new ResponseEntity<>(new ResponseMessage("Create owner success!"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> showListOwner(@RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        Iterable<OwnerDto> owners = ownerService.findAll();
        if (owners == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable Long id, @RequestBody OwnerDto ownerDto,
                                         @RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        Optional<OwnerDto> ownerDto1 = ownerService.findById(id);

        if (!ownerDto1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (ownerDto1.get().getName().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("The name is required"), HttpStatus.OK);
        }
        ownerDto1.get().setName(ownerDto.getName());
        ownerService.save(ownerDto1.get());
        return new ResponseEntity<>(new ResponseMessage("Update success!"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id,
                                         @RequestHeader("Authorization") final String authToken){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        Optional<OwnerDto> ownerDto = ownerService.findById(id);

        if (!ownerDto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ownerService.remove(ownerDto.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete success!"), HttpStatus.OK);
    }
}
