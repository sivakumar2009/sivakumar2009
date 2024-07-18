package com.demantiaapp.auth.repository;

import com.demantiaapp.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
