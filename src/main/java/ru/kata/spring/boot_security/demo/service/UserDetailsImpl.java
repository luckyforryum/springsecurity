package ru.kata.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.UserEntity;


@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;
    @Autowired
    public UserDetailsImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return new User(user.getUsername(),user.getPassword(),roleService.mapRolesToAuthorities(user.getRoles()));

    }

}
