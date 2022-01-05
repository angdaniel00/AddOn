package com.adds.addon.model.user;

import java.io.Serializable;
import java.util.Optional;

public class UserUpdateUModel implements Serializable {

    private Long id;
    private String name;
    private String lastname;
    private Optional<String> oldPassword;
    private Optional<String> newPassword;
    private String username;
    private String email;
    private int numberPhone;

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

    public String getOldPassword() {
        return oldPassword.get();
    }

    public void setOldPassword(Optional<String> oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword.get();
    }

    public void setNewPassword(Optional<String> newPassword) {
        this.newPassword = newPassword;
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

    public boolean isChangePassword(){
        return oldPassword.isPresent() && newPassword.isPresent();
    }
}
