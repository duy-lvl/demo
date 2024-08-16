package com.fpt.lab3.service;

import com.fpt.lab3.model.User;
import com.fpt.lab3.model.UserRole;
import com.fpt.lab3.repository.UserRepository;
import com.fpt.lab3.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Override
    public void add(User user) {
        String passwordEn = passwordEncoder.encode(user.getPassword()) ;
        user.setPassword(passwordEn);
        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRoleRepository.getById(2));
        user.setUserRoleSet(userRoleSet);
        userRepository.save(user);
    }
}
