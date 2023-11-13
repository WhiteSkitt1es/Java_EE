package com.http.mapper;

import com.http.dto.UsersDto;
import com.http.entity.Gender;
import com.http.entity.Role;
import com.http.entity.Users;
import com.http.util.LocalDateFormatter;

public class UsersMapper implements Mapper<UsersDto, Users> {

    private static final UsersMapper INSTANCE = new UsersMapper();
    private static final String IMAGE_FOLDER = "users/";

    private UsersMapper(){}

    @Override
    public Users mapFrom(UsersDto object) {
        return Users.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.find(object.getRole()).orElse(null))
                .gender(Gender.find(object.getGender()).orElse(null))
                .build();
    }
    public static UsersMapper getInstance(){
        return INSTANCE;
    }
}
