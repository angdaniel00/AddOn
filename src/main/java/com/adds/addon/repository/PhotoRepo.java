package com.adds.addon.repository;

import com.adds.addon.entities.Adds;
import com.adds.addon.entities.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository("photoRepo")
public interface PhotoRepo extends JpaRepository<Photos, Long>, CrudRepository<Photos, Long> {

    Optional<Collection<Photos>> findByAdds(Adds adds);
}
