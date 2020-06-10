package com.example.demo.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Role;

@Repository
public interface RoleReponsitory extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);

}
