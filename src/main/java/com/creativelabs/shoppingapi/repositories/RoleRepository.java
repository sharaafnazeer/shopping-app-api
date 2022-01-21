package com.creativelabs.shoppingapi.repositories;

import com.creativelabs.shoppingapi.entities.Role;
import com.creativelabs.shoppingapi.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
