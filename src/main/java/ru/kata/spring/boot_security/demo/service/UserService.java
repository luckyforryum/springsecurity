package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserEntity> getAllUsers();

    Optional<UserEntity> findByUsername(String username);

    public UserEntity getInfoByUsername(String username);

    public void save(UserEntity user);

    public void update(UserEntity user);

    public UserEntity getUser(Long id);
    public void delete(Long id);
}
