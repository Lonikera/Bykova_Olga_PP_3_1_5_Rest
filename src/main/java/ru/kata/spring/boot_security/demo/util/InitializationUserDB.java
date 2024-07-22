package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitializationUserDB {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitializationUserDB(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void createUsersWithRoles() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        Set<Role> set1 = new HashSet<>();
        set1.add(roleAdmin);
        Set<Role> set2 = new HashSet<>();
        set2.add(roleUser);

        User admin = new User("Альфия", "Дулькина", 20,  "alfi@mail.ru", "Root6tf7G", set1 );
        User user = new User("Глафира", "Репкина", 25,  "repka@mail.ru", "Root87tf78fD", set2 );

        // Шифрование паролей перед сохранением
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Сохранение пользователей в базу данных
        userService.saveUser(admin);
        userService.saveUser(user);

    }
}