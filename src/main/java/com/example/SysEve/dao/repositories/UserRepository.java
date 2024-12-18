package com.example.SysEve.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SysEve.dao.entities.User;

public interface UserRepository extends  JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String eamil);
}