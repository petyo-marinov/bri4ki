package bri4ka.repository;

import bri4ka.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u " +
            "FROM User AS u " +
            "WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("SELECT u " +
            "FROM User AS u " +
            "WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u " +
            "FROM User AS u " +
            "WHERE u.id = :id")
    Optional<User> findById(int id);

}
