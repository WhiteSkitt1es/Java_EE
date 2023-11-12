package com.http.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class UsersDto {
    String name;
    String birthday;
    Part image;
    String email;
    String password;
    String role;
    String gender;
}
