package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleDao {
    public Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles);
}
