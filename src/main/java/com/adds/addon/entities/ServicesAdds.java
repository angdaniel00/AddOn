package com.adds.addon.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Services")
public class ServicesAdds implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String service;

    @ManyToOne
    private Adds adds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Adds getAdds() {
        return adds;
    }

    public void setAdds(Adds adds) {
        this.adds = adds;
    }
}
