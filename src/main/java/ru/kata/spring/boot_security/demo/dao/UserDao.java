package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public void save(User user);
    public void update(User user);
    public void deleteById(Long id);
    public User findById(Long id);
    public User findByEmail(String email);

}
