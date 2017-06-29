package com.javasampleapproach.security.repository;

import com.javasampleapproach.security.model.Role;
import com.javasampleapproach.security.model.User;
import com.javasampleapproach.security.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRoleRepo extends JpaRepository<UserRoles,Long>{

//    @Transactional
//    @Query("UPDATE t_users_roles SET user_id=2 ,role_id=2 WHERE id=2;")
//    void queryByAlias(User user, Role role);
}
