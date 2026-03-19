package com.mycompany.mywebapp;

import com.mycompany.mywebapp.user.User;
import com.mycompany.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class MywebappVso24ApplicationTests {

    @Autowired
    private UserRepository repo;

    @Test
    void testAdduser() {
        User user = new User();
        user.setFirstName("Kadi");
        user.setLastName("Tamm");
        user.setEmail("kadi@voco.ee");
        user.setPassword("password123");

        User savedUser = repo.save(user);
        System.out.println(savedUser);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    void testUpdate(){
        Optional<User> optionalUser = repo.findById(5);
        User user = optionalUser.get();
        System.out.println(user);
        user.setLastName("Mänd");
        repo.save(user);

        User updatedUser = repo.findById(5).get();
        System.out.println(updatedUser);
        Assertions.assertThat(updatedUser.getLastName()).isEqualTo("Mänd");
    }

}
