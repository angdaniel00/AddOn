package com.adds.addon.model.user;

import com.adds.addon.entities.User;

import java.io.Serializable;
import java.util.Collection;

public class UserModel implements Serializable {

    protected String name;
    protected String lastname;
    protected String password;
    protected String username;
    protected String email;
    protected int numberPhone;
    protected Collection<String> roles;

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

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public User getUser(){
        return new User(name, lastname, password, username, email, numberPhone);
    }
}
