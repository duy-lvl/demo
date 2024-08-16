package com.fpt.lab3.service;

import com.fpt.lab3.model.User;
import com.fpt.lab3.model.UserRole;
import com.fpt.lab3.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customerUserDetailService")
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.warn("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true,
                true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        log.info("userId:{}, role:{}", user.getUserName(), user.getUserRoleSet());
        for (UserRole userProfile : user.getUserRoleSet()) {

            authorityList.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getName()));
        }
        return authorityList;
    }
}
