package com.tasko.Tasko.seeder;

import com.tasko.Tasko.model.Role;
import com.tasko.Tasko.model.User;
import com.tasko.Tasko.repository.RoleRepository;
import com.tasko.Tasko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("userpassword"));
        user.setRoles(Set.of(userRole));
        userRepository.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("adminpassword"));
        admin.setRoles(Set.of(adminRole));
        userRepository.save(admin);
    }
}
