package bri4ka.repository;

import bri4ka.model.pojo.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

    @Test
    @Transactional
    @DisplayName("Check if user exists by email")
    void itShouldCheckIfUserExistsByEmail() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("John.Doe");
        user.setEmail("john.doe@test.com");
        user.setPassword("John_Doe911");
        user.setAge(66);
        user.setAddress("Nowhere");

        underTest.save(user);

        String emailToSearch = "john.doe@test.com";
        Optional<User> result = underTest.findByEmail(emailToSearch);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    @Transactional
    @DisplayName("Check if user exists by username")
    void itShouldCheckIfUserExistsByUsername() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("John.Doe");
        user.setEmail("john.doe@test.com");
        user.setPassword("John_Doe911");
        user.setAge(66);
        user.setAddress("Nowhere");

        underTest.save(user);

        String usernameToSearch = "John.Doe";
        Optional<User> result = underTest.findByUsername(usernameToSearch);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    @Transactional
    @DisplayName("Check if user exists by ID")
    void itShouldCheckIfUserExistsById() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("John.Doe");
        user.setEmail("john.doe@test.com");
        user.setPassword("John_Doe911");
        user.setAge(66);
        user.setAddress("Nowhere");

        underTest.save(user);

        int userId = user.getId();
        Optional<User> result = underTest.findById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
}