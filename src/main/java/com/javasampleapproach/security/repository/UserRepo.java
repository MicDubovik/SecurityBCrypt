package com.javasampleapproach.security.repository;

import com.javasampleapproach.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Katy on 16.06.2017.
 */
public interface UserRepo extends JpaRepository<User,String> {

    User  findByUsername(String username);
    List<User> findAll();
}
