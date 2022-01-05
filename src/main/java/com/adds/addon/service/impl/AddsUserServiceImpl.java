package com.adds.addon.service.impl;

import com.adds.addon.entities.Adds;
import com.adds.addon.entities.User;
import com.adds.addon.repository.AddsRepo;
import com.adds.addon.repository.UserRepo;
import com.adds.addon.service.interf.AddsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddsUserServiceImpl implements AddsUserService {

    @Autowired
    @Qualifier("userRepo")
    private UserRepo userRepo;

    @Autowired
    @Qualifier("addsRepo")
    private AddsRepo repo;


    @Override
    public ResponseEntity getAdds(Long client) {
        Optional<User> userOptional = userRepo.findById(client);
        if (userOptional.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        User user = userOptional.get();
        return ResponseEntity.ok(repo.findByUser(user));
    }

    @Override
    public ResponseEntity getAdd(Long id) {
        Optional<Adds> optionalAdds = repo.findById(id);
        if (optionalAdds.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(optionalAdds.get());
    }

    @Override
    public ResponseEntity create(Adds adds) {
        if (adds.getPhotos().size()>Adds.MAX)
            return new ResponseEntity("Max of photo is 4",HttpStatus.BAD_REQUEST);
        repo.save(adds);
        return ResponseEntity.ok(adds);
    }

    @Override
    public ResponseEntity update(Adds adds) {
        if (adds.getPhotos().size()>Adds.MAX)
            return new ResponseEntity("Max of photo is 4",HttpStatus.BAD_REQUEST);
        repo.save(adds);
        return ResponseEntity.ok(adds);
    }

    @Override
    public ResponseEntity delete(Long id) {
        Optional<Adds> optionalAdds = repo.findById(id);
        if (optionalAdds.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        repo.delete(optionalAdds.get());
        return ResponseEntity.accepted().build();
    }
}
