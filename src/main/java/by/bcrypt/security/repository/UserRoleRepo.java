package by.bcrypt.security.repository;

import by.bcrypt.security.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleRepo extends JpaRepository<UserRoles,Long>{

//    @Transactional
//    @Query("UPDATE t_users_roles SET user_id=2 ,role_id=2 WHERE id=2;")
//    void queryByAlias(User user, Role role);
}
