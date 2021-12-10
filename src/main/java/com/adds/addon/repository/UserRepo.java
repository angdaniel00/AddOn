package com.adds.addon.repository;

import com.adds.addon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
