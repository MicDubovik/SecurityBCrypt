package com.javasampleapproach.security.controller;

import com.javasampleapproach.security.model.Role;
import com.javasampleapproach.security.model.User;
import com.javasampleapproach.security.model.UserRoles;
import com.javasampleapproach.security.repository.RoleRepo;
import com.javasampleapproach.security.repository.UserRepo;
import com.javasampleapproach.security.repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Valid;
import java.io.*;
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
    public String register() {
        return "register";
    }

    @RequestMapping(value = {"/adduser"}, method = RequestMethod.POST)
    public String newUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        String password = new BCryptPasswordEncoder().encode(user.getPasswd_hash());

        User newUser = new User(user.getUsername(), password);

        userRepo.saveAndFlush(newUser);
//
        userRepo.findByUsername(newUser.getUsername());

        Role role = roleRepo.findByAlias("ROLE_USER");

        userRoleRepo.saveAndFlush(new UserRoles(newUser, role));


        model.addAttribute("user", user);

        return "hello";
    }

//    public static void main(String[] args) throws IOException {
//
//        Properties property = new Properties();
//        try {
//            ClassLoader myCL = WebController.class.getClassLoader();
//            property.load(
//                    myCL.getResourceAsStream(
//                            "auth.properties"));
//            String post = property.getProperty("pass.postfix");
//        } catch (Exception x) {
//            x.printStackTrace();
//        }
//
//
//        String hashed = BCrypt.hashpw("111111", BCrypt.gensalt());
//
//
//// previously been hashed
//        if (BCrypt.checkpw("111111", hashed))
//            System.out.println("It matches");
//        else
//            System.out.println("It does not match");
//    }
}
