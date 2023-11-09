package com.http.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class UsersDto {
    String name;
    String birthday;
    String email;
    String password;
    String role;
    String gender;
}
