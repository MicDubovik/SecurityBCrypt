package by.bcrypt.security.repository;

import by.bcrypt.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Katy on 16.06.2017.
 */
public interface UserRepo extends JpaRepository<User,String> {

    User  findByUsername(String username);
    List<User> findAll();
}
