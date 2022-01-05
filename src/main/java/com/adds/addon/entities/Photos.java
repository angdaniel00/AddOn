package com.adds.addon.entities;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PhotosAdds")
public class Photos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private File photo;

    @Column
    private boolean portairt;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Adds adds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Adds getAdds() {
        return adds;
    }

    public void setAdds(Adds adds) {
        this.adds = adds;
    }

    public boolean isPortairt() {
        return portairt;
    }

    public void setPortairt(boolean portairt) {
        this.portairt = portairt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photos photos = (Photos) o;
        return Objects.equals(id, photos.id) &&
                Objects.equals(photo, photos.photo) &&
                Objects.equals(adds, photos.adds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photo, adds);
    }
}
