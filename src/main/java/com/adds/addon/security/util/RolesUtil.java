package com.adds.addon.security.util;

import com.adds.addon.entities.Privilege;
import com.adds.addon.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RolesUtil {

    public static List<String> getPrivilegies(Collection<Role> roles){
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

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege: privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
