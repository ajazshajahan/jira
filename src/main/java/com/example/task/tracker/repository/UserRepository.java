package com.example.task.tracker.repository;

import com.example.task.tracker.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthUser,Long> {
}
