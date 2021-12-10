package com.adds.addon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table
public class Role implements Serializable {

    public Role(){}

    public Role(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 25)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
        joinColumns = @JoinColumn(
                name = "role_id",
                referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
                name = "privilege_id",
                referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}
