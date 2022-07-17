package com.api.onlineboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String phoneNumber;
}
