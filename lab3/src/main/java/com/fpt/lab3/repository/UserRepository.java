package com.fpt.lab3.repository;

import com.fpt.lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userName=?1")
    public User findByUsername(String userName);
}
