package com.http.dao;

import com.http.entity.Users;
import com.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersDao implements Dao<Integer, Users> {

    private static final UsersDao INSTANCE = new UsersDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, image, password, role, gender)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private UsersDao() {

    }

    public static UsersDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public Optional<Users> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Users entity) {

    }

    @Override
    @SneakyThrows
    public Users save(Users entity) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getImage());
            preparedStatement.setObject(5, entity.getPassword());
            preparedStatement.setObject(6, entity.getRole().name());
            preparedStatement.setObject(7, entity.getGender().name());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                entity.setId(generatedKeys.getObject(1, Integer.class));
            }

            return entity;
        }
    }
}
