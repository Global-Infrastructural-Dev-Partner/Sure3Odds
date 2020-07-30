package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.MemberAdvisers;

@Repository
public interface MemberAdvisersRepository extends JpaRepository<MemberAdvisers, Long> {

}
