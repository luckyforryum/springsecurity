package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    @Autowired
    public UserDetailsImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return new User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));

    }

    public UserEntity getInfoByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
        return user;
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }


    public  UserEntity save (UserEntity userEntity) {
//        UserEntity user = new UserEntity();
//        user.setFirstname(userEntity.getFirstname());
//        user.setLastname(userEntity.getFirstname());
//        user.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//        userEntity.setRoles(Arrays.asList(new Role(userEntity.getRoles())));
//        user.setRoles(Arrays.asList(new Role(userEntity.getRoles())));
        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getUser(Long id) {
        return userRepository.getById(id);
    }

//    public Optional<UserEntity> getUser(String username) {
//        return userRepository.findByUsername(username);
//    }


    public void delete (Long id) {
        userRepository.deleteById(id);
    }

}
