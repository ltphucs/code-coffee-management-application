package com.nobita.demo.model;

import com.nobita.demo.model.en.Position;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Staff {
    private Long id;
    private LocalDate dateJoin;

    @Size(min = 3, max = 50, message = "Họ và Tên phải từ trong khoảng từ [3-50] ký tự")
    private String fullName;

    private String gender;
    private Position position;

    @Pattern(regexp = "(0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-[12]\\d{3}", message = "Sai định dạng ngày. Ví dụ: 01-01-2000")
    private LocalDate dateOfBirth;

    @Size(min = 3, message = "Độ dài địa chỉ ít nhất 3 ký tự")
    private String address;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Lỗi nhập sai định dạng số điện thoại")
    private String phone;

    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{3,50}$)(?!.*[_.]{2})[^_.].*[^_.]$",
            message = "Tên nhân viên phải trong khoảng[3-25] và không được có ký tự đặc biệt")
    private String username;

    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{4,50}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$",
        message = "Độ dài email phải trong khoảng[4-50] và phải đúng định dạng của nó"
    )
    private String email;
    private String password;
}
