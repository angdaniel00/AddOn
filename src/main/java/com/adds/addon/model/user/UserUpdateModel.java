package com.adds.addon.model.user;

import com.adds.addon.entities.User;

import java.util.Date;
import java.util.Optional;

public class UserUpdateModel extends UserModel {

    private Long id;
    private Optional<String> oldPassword;
    private Optional<String> newPassword;
    private Date datePay;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isChangePassword(){
        return oldPassword.isPresent() && newPassword.isPresent();
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

    @Override
    public User getUser() {
        return null;
    }
}
