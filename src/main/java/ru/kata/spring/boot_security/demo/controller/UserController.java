package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserDetailsImpl;

import java.security.Principal;
import java.util.Optional;


@Controller
public class UserController {

    private final UserDetailsImpl userDetails;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserDetailsImpl userDetails, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.userDetails = userDetails;

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    @GetMapping("/user")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String pageForUser(Principal principal, Model model) {
        UserEntity userEntity1 =  userDetails.getInfoByUsername(principal.getName());
        model.addAttribute("thisUser",userEntity1);
        return "user";
//        System.out.println(userEntity.getUsername());
//        System.out.println(userEntity.getPassword());
//        System.out.println(userEntity.getClass());
//        System.out.println(userDetails.loadUserByUsername(userEntity.getUsername()));

//        System.out.println(userDetails.loadUserByUsername(userEntity.getUsername()));
//        UserEntity userEntity1 = userRepository.findByUsername(userEntity.getUsername()).orElseThrow(
//                () -> new UsernameNotFoundException("User not found"));
//        System.out.println(userDetails.loadUserByUsername(userEntity.getUsername()));
//    UserEntity userEntity1 =  userDetails.getInfo(userEntity.getUsername());

    }

}
