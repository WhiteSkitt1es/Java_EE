package com.http.dao;

import com.http.entity.Gender;
import com.http.entity.Role;
import com.http.entity.Users;
import com.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UsersDao implements Dao<Integer, Users> {

    private static final UsersDao INSTANCE = new UsersDao();

    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, image, password, role, gender)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD = """
            SELECT * FROM users WHERE email = ? AND password = ?;
            """;

    private UsersDao() {

    }

    public static UsersDao getInstance() {
        return INSTANCE;
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
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject(1, Integer.class));
            }

            return entity;
        }
    }

    @SneakyThrows
    public Optional<Users> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD);) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            Users users = null;
            if (resultSet.next()){
                users = buildEntity(resultSet);
            }
            return Optional.ofNullable(users);
        }
    }

    private static Users buildEntity(ResultSet resultSet) throws SQLException {
        return Users.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .image(resultSet.getObject("image", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .gender(Gender.find(resultSet.getObject("gender", String.class)).orElse(null))
                .build();
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

}
