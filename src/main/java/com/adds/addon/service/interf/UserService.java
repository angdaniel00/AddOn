package com.adds.addon.service.interf;

import com.adds.addon.model.user.LoginModel;
import com.adds.addon.model.user.UserModel;
import com.adds.addon.model.user.UserUpdateModel;
import com.adds.addon.model.user.UserUpdateUModel;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    ResponseEntity get(Long id);
    ResponseEntity getName(String name);
    ResponseEntity get();
    ResponseEntity update(UserUpdateModel user);
    ResponseEntity update(UserUpdateUModel user);
    ResponseEntity createUser(UserModel userModel);
    ResponseEntity deleteUser(Long id);
    ResponseEntity login(LoginModel login, HttpServletResponse response);

}
