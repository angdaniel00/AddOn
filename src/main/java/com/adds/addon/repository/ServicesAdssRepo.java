package com.adds.addon.repository;

import com.adds.addon.entities.Adds;
import com.adds.addon.entities.ServicesAdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ServicesAdssRepo extends JpaRepository<ServicesAdds, Long>, CrudRepository<ServicesAdds, Long> {

    Optional<Collection<ServicesAdds>> findByAdds(Adds adds);
}
