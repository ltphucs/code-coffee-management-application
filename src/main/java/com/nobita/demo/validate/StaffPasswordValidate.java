package com.nobita.demo.validate;

import com.nobita.demo.model.Staff;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StaffPasswordValidate implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Staff.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Staff staff = (Staff) o;

        String password = staff.getPassword();
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")){
            errors.rejectValue("password", null,"Độ dài mật khẩu ít nhất 8 ký tự, ít nhất một chữ cái và một số");
        }
    }
}
