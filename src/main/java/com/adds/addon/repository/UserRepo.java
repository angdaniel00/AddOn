package com.adds.addon.repository;

import com.adds.addon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Long>, CrudRepository<User, Long>,
        PagingAndSortingRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<Collection<User>> findByUsernameLike(String username);
    boolean existsByUsername(String username);
}
