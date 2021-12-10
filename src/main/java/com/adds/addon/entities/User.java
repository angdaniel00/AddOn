package com.adds.addon.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String lastname;

    @Transient
    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 8, unique = true, nullable = false)
    private int numberPrhone;

    @Column
    private boolean status;

    @Column(name = "pay")
    private Date datePay;

    @OneToMany
    private Collection<Adds> adds;

    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberPrhone() {
        return numberPrhone;
    }

    public void setNumberPrhone(int numberPrhone) {
        this.numberPrhone = numberPrhone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public Collection<Adds> getAdds() {
        return adds;
    }

    public void setAdds(Collection<Adds> adds) {
        this.adds = adds;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
