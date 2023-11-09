package com.http.mapper;

import com.http.dto.UsersDto;
import com.http.entity.Gender;
import com.http.entity.Role;
import com.http.entity.Users;
import com.http.util.LocalDateFormatter;

public class UsersMapper implements Mapper<UsersDto, Users> {

    private static final UsersMapper INSTANCE = new UsersMapper();

    private UsersMapper(){}

    @Override
    public Users mapFrom(UsersDto object) {
        return Users.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .gender(Gender.valueOf(object.getGender()))
                .build();
    }
    public static UsersMapper getInstance(){
        return INSTANCE;
    }
}
