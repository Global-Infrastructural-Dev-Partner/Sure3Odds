package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gidp.sure3odds.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
