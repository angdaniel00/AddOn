package com.adds.addon.repository;

import com.adds.addon.entities.Adds;
import com.adds.addon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository("addsRepo")
public interface AddsRepo extends JpaRepository<Adds, Long>, CrudRepository<Adds, Long>,
                                  PagingAndSortingRepository<Adds, Long> {

    Optional<Collection<Adds>> findByDespcriptionLike(String description);
    Optional<Collection<Adds>> findByUser(User user);
}
