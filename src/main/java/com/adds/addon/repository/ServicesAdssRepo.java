package com.adds.addon.repository;

import com.adds.addon.entities.Adds;
import com.adds.addon.entities.ServicesAdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository("servicesAddsRepo")
public interface ServicesAdssRepo extends JpaRepository<ServicesAdds, Long>, CrudRepository<ServicesAdds, Long> {

    Optional<Collection<ServicesAdds>> findByAdds(Adds adds);
}
