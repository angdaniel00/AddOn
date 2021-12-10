package com.adds.addon.entities;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table
public class Photos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private File photo;

    @ManyToOne
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
}
