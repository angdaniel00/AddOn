package com.adds.addon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "PrivilegeUser")
public class Privilege implements Serializable {

    public Privilege(){}

    public Privilege(String name){
        this.name = name;
    }

    public Privilege(String name, Collection<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Basic
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
