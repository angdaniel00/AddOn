package com.adds.addon.repository;

import com.adds.addon.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<Role, Long>, CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
