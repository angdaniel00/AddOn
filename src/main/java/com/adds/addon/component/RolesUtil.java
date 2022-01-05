package com.adds.addon.component;

import com.adds.addon.entities.Privilege;
import com.adds.addon.entities.Role;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RolesUtil {

    public final static Key KEY = Keys.keyPairFor(SignatureAlgorithm.RS256).getPrivate();
    public static final String[] ROLES = {"ROLE_USER", "ROLE_ADMIN"};
    public static final String[] PRIVILEGES = {"READ_PRIVILEGE", "WRITE_PRIVILEGE"};

    @Transactional
    public List<String> getPrivilegies(@NonNull Collection<Role> roles){
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role: roles){
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item: collection){
            privileges.add(item.getName());
        }
        return privileges;
    }

    @Transactional
    public List<GrantedAuthority> getGrantedAuthorities(@NonNull List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege: privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
