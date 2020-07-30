package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidp.sure3odds.entity.Leagues;

@Repository
public interface LeaguesRepository extends JpaRepository<Leagues, Long> {

}
