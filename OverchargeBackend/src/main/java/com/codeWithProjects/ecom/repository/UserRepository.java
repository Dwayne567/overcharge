package com.codeWithProjects.ecom.repository;

import com.codeWithProjects.ecom.entity.User;
import com.codeWithProjects.ecom.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByNameContaining(String name);

	Optional<User> findFirstByEmail(String email);

	User findByRole(UserRole userRole);
}
