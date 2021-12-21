package com.dave.todos.usersservice.repositories;

import com.dave.todos.usersservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
