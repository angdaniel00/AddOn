package com.adds.addon.security;

import com.adds.addon.repository.UserRepo;
import com.adds.addon.component.RolesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo users;

    @Autowired
    private RolesUtil rolesUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.adds.addon.entities.User> userEntityOptional = users.findByUsername(username);
        if(userEntityOptional.isEmpty())
            throw new UsernameNotFoundException(username);
        com.adds.addon.entities.User user = userEntityOptional.get();

        return new User(user.getUsername(), user.getPassword(),
                rolesUtil.getGrantedAuthorities(rolesUtil.getPrivilegies(user.getRoles())));
    }
}
