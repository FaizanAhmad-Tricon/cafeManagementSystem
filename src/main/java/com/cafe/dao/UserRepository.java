package com.cafe.dao;

import com.cafe.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User  findByEmail(String  email);




    List<User> findByRole(String role);
}
