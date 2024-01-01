package com.ecom.registrationapi.dao;

import com.ecom.registrationapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
