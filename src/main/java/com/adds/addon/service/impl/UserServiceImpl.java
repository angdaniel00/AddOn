package com.adds.addon.service.impl;

import com.adds.addon.component.RolesUtil;
import com.adds.addon.entities.Role;
import com.adds.addon.entities.User;
import com.adds.addon.model.user.LoginModel;
import com.adds.addon.model.user.UserModel;
import com.adds.addon.model.user.UserUpdateModel;
import com.adds.addon.model.user.UserUpdateUModel;
import com.adds.addon.repository.RoleRepo;
import com.adds.addon.repository.UserRepo;
import com.adds.addon.service.interf.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepo")
    private UserRepo userRepo;

    @Autowired
    @Qualifier("roleRepo")
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RolesUtil rolesUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> get(Long id) {
        Optional<User> optional = userRepo.findById(id);
        if (optional.isEmpty())
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(optional.get());
    }

    @Override
    public ResponseEntity<?> getName(String name) {
        Optional<Collection<User>> optional = userRepo.findByUsernameLike(name);
        if (optional.isEmpty())
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(optional.get());
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @Override
    public ResponseEntity<?> update(UserUpdateModel user) {
        Optional<User> optional = userRepo.findById(user.getId());
        if (optional.isEmpty())
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        User userUpdate = optional.get();
        if (user.isChangePassword()){
            if (bCryptPasswordEncoder.matches(user.getOldPassword(), userUpdate.getPassword()))
                userUpdate.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
            else
                return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
        }
        assignRoles(userUpdate, user.getRoles());
        userUpdate.setName(user.getName());
        userUpdate.setLastname(user.getLastname());
        userUpdate.setStatus(user.isStatus());
        userUpdate.setDatePay(user.getDatePay());
        userUpdate.setNumberPhone(user.getNumberPhone());
        userUpdate.setEmail(user.getEmail());
        userRepo.save(userUpdate);
        return ResponseEntity.ok(userUpdate);
    }

    @Override
    public ResponseEntity<?> update(UserUpdateUModel user) {
        Optional<User> optional = userRepo.findById(user.getId());
        if (optional.isEmpty())
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        User userUpdate = optional.get();
        if (user.isChangePassword()){
            if (bCryptPasswordEncoder.matches(user.getOldPassword(), userUpdate.getPassword()))
                userUpdate.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
            else
                return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
        }
        userUpdate.setName(user.getName());
        userUpdate.setLastname(user.getLastname());
        userUpdate.setNumberPhone(user.getNumberPhone());
        userUpdate.setEmail(user.getEmail());
        userRepo.save(userUpdate);
        return ResponseEntity.ok(userUpdate);
    }

    @Override
    public ResponseEntity<User> createUser(UserModel userModel) {
        User user = userModel.getUser();
        assignRoles(user, userModel.getRoles());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty())
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        userRepo.delete(user.get());
        return ResponseEntity.accepted().build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> login(LoginModel login, HttpServletResponse response) {
        com.adds.addon.entities.User user;
        if (!userRepo.existsByUsername(login.getUsername())){
            return new ResponseEntity<String>("Credentials error", HttpStatus.UNAUTHORIZED);
        }
        try{
            Optional<com.adds.addon.entities.User> optionalUser = userRepo.findByUsername(login.getUsername());
            if (optionalUser.isPresent()){
                user = optionalUser.get();
            }
            else{
                return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(),
                    rolesUtil.getGrantedAuthorities(rolesUtil.getPrivilegies(user.getRoles())));
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            if (!authentication.isAuthenticated()){
                throw new AuthenticationCredentialsNotFoundException("Credentials incorrect");
            }
            String token = Jwts.builder().setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis()+86400000))
                    .signWith(RolesUtil.KEY).compact();
            response.addHeader("Authorization", token);
        }
        catch (AuthenticationException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().build();
    }

    private void assignRoles(User user, Collection<String> roles){
        Collection<Role> rolesUser = new ArrayList<Role>();
        for (Role role : roleRepo.findAll()) {
            if (roles.contains(role.getName()))
                rolesUser.add(role);
        }
        user.setRoles(rolesUser);
    }
}
