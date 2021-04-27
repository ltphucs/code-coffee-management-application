package com.nobita.demo.model;


import com.nobita.demo.model.en.Authorization;
import com.nobita.demo.validate.PasswordValidate;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Collection;

@Data
public class Account  implements UserDetails {
    private Long id;

    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{3,50}$)(?!.*[_.]{2})[^_.].*[^_.]$",
            message = "Tên người dùng phải trong khoảng[3-25] và không được có ký tự đặc biệt")
    private String username;

    private String password;

    private Authorization authorization;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
