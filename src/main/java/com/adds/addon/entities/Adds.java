package com.adds.addon.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "AddsPromotional")
public class Adds implements Serializable {

    public static final int MAX = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Basic
    private String despcription;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, optional = false)
    private User user;

    @Column
    private boolean active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adds adds = (Adds) o;
        return Objects.equals(id, adds.id) &&
                Objects.equals(despcription, adds.despcription) &&
                Objects.equals(user, adds.user) &&
                Objects.equals(servicesAdds, adds.servicesAdds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, despcription, user, photos, servicesAdds);
    }
}
