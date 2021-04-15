package com.nobita.demo.model;

import com.nobita.demo.model.en.Authorization;
import com.nobita.demo.validate.PasswordValidate;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Account extends PasswordValidate {
    private Long id;

    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{3,50}$)(?!.*[_.]{2})[^_.].*[^_.]$",
            message = "Tên người dùng phải trong khoảng[3-25] và không được có ký tự đặc biệt")
    private String username;

    private String password;

    private Authorization authorization;



}
