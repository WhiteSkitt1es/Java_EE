package com.http.mapper;

import com.http.dto.UserDto;
import com.http.entity.Users;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<Users, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public UserDto mapFrom(Users object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .image(object.getImage())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }
    public static UserMapper getInstance(){
        return INSTANCE;
    }
}
