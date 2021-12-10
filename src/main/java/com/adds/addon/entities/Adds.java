package com.adds.addon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AddsCuba")
public class Adds implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String despcription;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column
    @OneToMany
    private Collection<Photos> photos;

    @Column
    @OneToMany
    private Collection<ServicesAdds> servicesAdds;
}
