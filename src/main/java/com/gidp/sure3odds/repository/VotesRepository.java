package com.gidp.sure3odds.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.gidp.sure3odds.entity.Votes;

public interface VotesRepository extends JpaRepository<Votes, Long> {

}
