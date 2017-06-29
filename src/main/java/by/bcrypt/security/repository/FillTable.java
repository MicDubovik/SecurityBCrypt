package by.bcrypt.security.repository;

import by.bcrypt.security.model.Role;
import by.bcrypt.security.model.User;
import by.bcrypt.security.model.UserRoles;
import by.bcrypt.security.controller.WebController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class FillTable implements CommandLineRunner {

    UserRepo repo;
    RoleRepo roleRepo;
    UserRoleRepo userRoleRepo;

    @Autowired
    public FillTable(UserRepo repo, RoleRepo roleRepo, UserRoleRepo userRoleRepo) {
        this.repo = repo;
        this.roleRepo = roleRepo;
        this.userRoleRepo = userRoleRepo;
    }

    public String getPostfix() {
        Properties property = new Properties();
        String postfix = null;
        try {
            ClassLoader myCL = WebController.class.getClassLoader();
            property.load(
                    myCL.getResourceAsStream(
                            "auth.properties"));
            postfix = property.getProperty("pass.postfix");
        } catch (Exception x) {
            x.printStackTrace();
        }
        return postfix;
    }

    @Override
    public void run(String... strings) throws Exception {

        Role role = new Role("ROLE_ADMIN","ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER","ROLE_USER");
        User user1 = new User("michyy",new BCryptPasswordEncoder().encode("111111"+getPostfix()));
        User user2 = new User("vano",new BCryptPasswordEncoder().encode("222222"+getPostfix()));



        List<User> users = repo.findAll();
//        repo.deleteAll();
        if (users.size()==0){
            repo.saveAndFlush(user1);
            repo.saveAndFlush(user2);
            roleRepo.saveAndFlush(role);
            roleRepo.saveAndFlush(role2);
            userRoleRepo.saveAndFlush(new UserRoles(user1,role));
            userRoleRepo.saveAndFlush(new UserRoles(user2,role2));

        }

    }
}
