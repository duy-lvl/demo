package com.fpt.lab3.repository;

import com.fpt.lab3.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
