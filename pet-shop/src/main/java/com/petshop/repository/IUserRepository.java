package com.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
