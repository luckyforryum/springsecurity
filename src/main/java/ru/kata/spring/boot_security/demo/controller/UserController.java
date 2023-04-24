package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserDetailsImpl;
import java.security.Principal;



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
    public String pageForUser(Principal principal, Model model) {
        UserEntity userEntity1 =  userDetails.getInfoByUsername(principal.getName());
        model.addAttribute("thisUser",userEntity1);
        return "user";
    }

}
