package by.bcrypt.security.repository;

import by.bcrypt.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Katy on 17.06.2017.
 */
public interface RoleRepo extends JpaRepository<Role,Long>{

     Role findByAlias(String alias);
}
