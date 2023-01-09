package com.carrental.model.repository;

import com.carrental.model.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Page<Owner> findAllByNameContaining(String ownerName, Pageable pageable);
    @Query(
            nativeQuery = true,
            value = "SELECT o.name FROM owners o " +
                    "INNER JOIN car c ON c.car_id = o.id " +
                    "WHERE c.id = (:carId);"
    )
    List<String> findOwnerNameByCarId(@Param("carId") Long carId);
}

