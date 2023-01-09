package com.carrental.model.service.impl;

import com.carrental.model.dto.OwnerDto;
import com.carrental.model.entity.Owner;
import com.carrental.model.repository.OwnerRepository;
import com.carrental.model.service.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@ComponentScan(basePackageClasses = ModelMapper.class)
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final ModelMapper modelMapper;

    public OwnerServiceImpl(OwnerRepository ownerRepository, ModelMapper modelMapper) {
        this.ownerRepository = ownerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<OwnerDto> findAll() {
        Iterable<Owner> entities = ownerRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, OwnerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OwnerDto> findById(Long id) {
        Owner entity = ownerRepository.findById(id).orElse(null);
        return Optional.ofNullable(modelMapper.map(entity, OwnerDto.class));
    }

    @Override
    public void save(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        ownerRepository.save(owner);
    }

    @Override
    public void remove(Long id) {
        ownerRepository.deleteById(id);
    }
}
