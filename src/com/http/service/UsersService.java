package com.http.service;

import com.http.dao.UsersDao;
import com.http.dto.UsersDto;
import com.http.entity.Users;
import com.http.exception.ValidationException;
import com.http.mapper.UsersMapper;
import com.http.validator.UsersValidator;
import com.http.validator.ValidationResult;
import lombok.SneakyThrows;

public class UsersService {

    private static final UsersService INSTANCE = new UsersService();
    private final UsersDao usersDao = UsersDao.getInstance();
    private final UsersValidator usersValidator = UsersValidator.getInstance();
    private final UsersMapper usersMapper = UsersMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private UsersService(){

    }
    @SneakyThrows
    public Integer create(UsersDto usersDto){
        ValidationResult validationResult = usersValidator.isValid(usersDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        Users usersEntity = usersMapper.mapFrom(usersDto);
        imageService.upload(usersEntity.getImage(), usersDto.getImage().getInputStream());
        usersDao.save(usersEntity);
        return usersEntity.getId();
    }
    public static UsersService getInstance(){
        return INSTANCE;
    }
}
