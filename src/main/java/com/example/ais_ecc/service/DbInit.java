package com.example.ais_ecc.service;

import com.example.ais_ecc.entity.Role;
import com.example.ais_ecc.entity.User;
import com.example.ais_ecc.repositories.ActionRepository;
import com.example.ais_ecc.repositories.RoleRepository;
import com.example.ais_ecc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class DbInit {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String createOrdesrs;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    ActionRepository actionRepository;


    BCryptPasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        passwordEncoder = new BCryptPasswordEncoder();
    }


    public void Init() throws Exception {

        try {
            if (createOrdesrs.equals("create")) {

                CreateRoles();
                CreateUsers();
                TestActions();
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            throw new Exception("Ошибка при автозаполнении базы данных");
        }

    }

    private void CreateRoles() throws Exception {


        var roles = new ArrayList<String>();
        roles.add("ROLE_ADMIN"); // Admin
        roles.add("ROLE_USER"); // User
        roles.add("ROLE_INVESTIGATOR"); // Следователь
        roles.add("ROLE_PROSECUTOR"); // Прокурор


        for (var role : roles)
            if (!roleRepository.existsByName(role))
                roleRepository.save(new Role(role));
    }


    void CreateUsersPattern(String roleWithoutROLE_, int count) {

        var role = roleRepository.findRoleByName("ROLE_" + roleWithoutROLE_).get();

        var password = passwordEncoder.encode("123");

        for (int i = 0; i <= count; i++) {
            var username = roleWithoutROLE_ + i;
            var email = roleWithoutROLE_.toLowerCase() + i + "@mail.ru";
            User newUser = new User(username, email, password);
            newUser.setRadnomHuman();


            newUser.addRole(role);
            if (!userRepository.existsByEmail(email))
                userRepository.save(newUser);
        }
    }


    void CreateUsers() throws Exception {

        try {
            CreateUsersPattern("ADMIN", 1);
            CreateUsersPattern("USER", 10);
            CreateUsersPattern("INVESTIGATOR", 10);
            CreateUsersPattern("PROSECUTOR", 10);


        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            throw new Exception("Ошибка при автозаполнении базы данных");
        }

    }

    void TestActions() {


    }

}
