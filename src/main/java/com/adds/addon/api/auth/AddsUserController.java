package com.adds.addon.api.auth;

import com.adds.addon.entities.Adds;
import com.adds.addon.service.interf.AddsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("auth/client/")
public class AddsUserController {

    @Autowired
    private AddsUserService addsUserService;

    @GetMapping("{client}")
    public ResponseEntity<?> getListAdds(@PathVariable("client") Long client){
        return addsUserService.getAdds(client);
    }

    @GetMapping("adds/{id}")
    public ResponseEntity<?> getAdds(@PathVariable("id") Long id){
        return addsUserService.getAdd(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody Adds adds){
        return addsUserService.create(adds);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Adds adds){
        return addsUserService.update(adds);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return addsUserService.delete(id);
    }
}
