package com.adds.addon.service.interf;

import com.adds.addon.entities.Adds;
import org.springframework.http.ResponseEntity;

public interface AddsUserService {

    ResponseEntity getAdds(Long client);
    ResponseEntity getAdd(Long id);
    ResponseEntity create(Adds adds);
    ResponseEntity update(Adds adds);
    ResponseEntity delete(Long id);

}
