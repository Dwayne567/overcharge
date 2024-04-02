package com.spark.overcharge.repository;

import com.spark.overcharge.entity.User;
import com.spark.overcharge.enums.UserRole;

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
