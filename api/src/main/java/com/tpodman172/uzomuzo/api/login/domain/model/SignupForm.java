package com.tpodman172.uzomuzo.api.login.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SignupForm {
    private String userId;
    private String password;
    private String userName;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    private int age;
    private boolean marriage;

}
