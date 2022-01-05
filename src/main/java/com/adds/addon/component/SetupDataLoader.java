package com.adds.addon.component;

import com.adds.addon.entities.Privilege;
import com.adds.addon.entities.Role;
import com.adds.addon.repository.PrivilegeRepo;
import com.adds.addon.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PrivilegeRepo privilegeRepo;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));
        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege;
        Optional<Privilege> optPrivilege = privilegeRepo.findByName(name);
        if (optPrivilege.isEmpty()){
            privilege = new Privilege(name);
            privilegeRepo.save(privilege);
        }
        else
            privilege = optPrivilege.get();
        return privilege;
    }

    @Transactional
    void createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role;
        Optional<Role> optRole = roleRepo.findByName(name);
        if (optRole.isEmpty()){
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepo.save(role);
        }
        else{
            role = optRole.get();
            if (!role.getPrivileges().containsAll(privileges)){
                role.setPrivileges(privileges);
                roleRepo.save(role);
            }
        }
    }
}
