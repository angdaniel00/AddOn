package com.adds.addon.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table
public class Adds implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String despcription;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @OneToMany
    private Collection<Photos> photos;

    @OneToMany
    private Collection<ServicesAdds> servicesAdds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDespcription() {
        return despcription;
    }

    public void setDespcription(String despcription) {
        this.despcription = despcription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photos> photos) {
        this.photos = photos;
    }

    public Collection<ServicesAdds> getServicesAdds() {
        return servicesAdds;
    }

    public void setServicesAdds(Collection<ServicesAdds> servicesAdds) {
        this.servicesAdds = servicesAdds;
    }
}
