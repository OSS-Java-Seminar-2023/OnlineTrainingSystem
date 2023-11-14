package com.training.OnlineTraining.repository;

import com.training.OnlineTraining.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndPassword(String email, String password);

}