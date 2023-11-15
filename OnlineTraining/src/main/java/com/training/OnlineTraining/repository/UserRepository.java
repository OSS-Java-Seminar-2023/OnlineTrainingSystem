package com.training.OnlineTraining.repository;

import com.training.OnlineTraining.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndPassword(String email, String password);


    Optional<User> findByEmail(String email);

}