package com.gidp.sure3odds.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.users.UserTypes;

import java.util.Optional;

@Repository
public interface UserTypesRepository extends JpaRepository<UserTypes, Long> {

    Optional<UserTypes> findByName(String userType);

}
