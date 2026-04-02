package it.pittysoft.affetti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.pittysoft.affetti.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByRole(String role);
}
