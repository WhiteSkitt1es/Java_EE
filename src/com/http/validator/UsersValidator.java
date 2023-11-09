package com.http.validator;

import com.http.dto.UsersDto;
import com.http.entity.Gender;
import com.http.entity.Role;
import com.http.util.LocalDateFormatter;

public class UsersValidator implements Validator<UsersDto>{

    private static final UsersValidator INSTANCE = new UsersValidator();

    @Override
    public ValidationResult isValid(UsersDto object) {
        ValidationResult validationResult = new ValidationResult();
        if(String.valueOf(object.getName()) == null){
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }
        if(!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if(String.valueOf(object.getEmail()) == null){
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        if(String.valueOf(object.getPassword()) == null){
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
        }
        if(object.getRole() == null){
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        if(object.getGender() == null){
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }

        return validationResult;
    }
    public static UsersValidator getInstance(){
        return INSTANCE;
    }
}
