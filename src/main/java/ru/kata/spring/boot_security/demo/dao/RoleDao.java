package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();
    void save(Role role);
    Role findById(Long id);
    void deleteById(Long id);
    Role getRoleByName(String name);
}
