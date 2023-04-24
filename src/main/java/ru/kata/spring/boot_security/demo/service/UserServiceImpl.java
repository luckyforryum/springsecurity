package ru.kata.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public Optional<UserEntity> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public UserEntity getInfoByUsername(String username) {
        return userDao.getInfoByUsername(username);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(UserEntity user) {
        userDao.update(user);
    }

    @Override
    public UserEntity getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}
