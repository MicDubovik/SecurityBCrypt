package by.bcrypt.security.controller;

import by.bcrypt.security.model.Role;
import by.bcrypt.security.model.User;
import by.bcrypt.security.model.UserRoles;
import by.bcrypt.security.repository.RoleRepo;
import by.bcrypt.security.repository.UserRepo;
import by.bcrypt.security.repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Properties;

@Controller
public class WebController {

    UserRepo userRepo;

    RoleRepo roleRepo;

    UserRoleRepo userRoleRepo;

    @Autowired
    public WebController(UserRepo userRepo, RoleRepo roleRepo, UserRoleRepo userRoleRepo) {
        this.userRepo = userRepo;
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


    @RequestMapping(value = {"/", "home"})
    public String home() {
        return "home";
    }

    @RequestMapping(value = {"/welcome"})
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = {"/login"})
    public String login(Model model) {
         String postfix = getPostfix();

         model.addAttribute("postfix",postfix);
        return "login";
    }


    @RequestMapping(value = "/403")
    public String Error403() {
        return "403";
    }

    @RequestMapping(value = {"/register"})
    public String register(Model model) {
        String postfix = getPostfix();

        model.addAttribute("postfix",postfix);

        return "register";
    }

    @RequestMapping(value = {"/adduser"}, method = RequestMethod.POST)
    public String newUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        String hashPass = user.getPasswd_hash();

        String pass = user.getPasswd_hash().substring(0,getPostfix().length());

        User showUser = new User(user.getUsername(),user.getPasswd_hash().substring(0,getPostfix().length()));

        User newUser = new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPasswd_hash()));

        userRepo.saveAndFlush(newUser);
//
        userRepo.findByUsername(newUser.getUsername());

        Role role = roleRepo.findByAlias("ROLE_USER");

        userRoleRepo.saveAndFlush(new UserRoles(newUser, role));


        model.addAttribute("user", showUser);

        return "hello";
    }


}
