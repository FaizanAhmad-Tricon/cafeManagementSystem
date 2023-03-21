package com.cafe.dao;

import com.cafe.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User  findByEmail(String  email);
}
