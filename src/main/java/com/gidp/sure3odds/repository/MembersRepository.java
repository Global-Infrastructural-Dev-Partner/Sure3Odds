package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gidp.sure3odds.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

}
