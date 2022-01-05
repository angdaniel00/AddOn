package com.adds.addon.api.auth;

import com.adds.addon.model.user.UserModel;
import com.adds.addon.model.user.UserUpdateModel;
import com.adds.addon.model.user.UserUpdateUModel;
import com.adds.addon.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity get(){
        return userService.get();
    }

    @GetMapping("findById/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity getId(@PathVariable("id") Long id){
        return userService.get(id);
    }

    @GetMapping("findByUsername/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getLikeName(@PathVariable("username") String username){
        return userService.getName(username);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity createUser(@Validated @RequestBody UserModel userModel){
        return userService.createUser(userModel);
    }

    @PutMapping("userclient")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity updateUser(@Validated @RequestBody UserUpdateUModel userModel){
        return userService.update(userModel);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity updateUser(@Validated @RequestBody UserUpdateModel userModel){
        return userService.update(userModel);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }

}
