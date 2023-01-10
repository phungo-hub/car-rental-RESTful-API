package com.carrental.model.repository;

import com.carrental.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);

    @Query(nativeQuery = true,
            value = "SELECT r.name FROM roles r " +
                    "INNER JOIN users u ON r.id = u.role_id " +
                    "WHERE u.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);

}