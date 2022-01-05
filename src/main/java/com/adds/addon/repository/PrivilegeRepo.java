package com.adds.addon.repository;

import com.adds.addon.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("privilegeRepo")
public interface PrivilegeRepo extends JpaRepository<Privilege, Long>, CrudRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);
}
